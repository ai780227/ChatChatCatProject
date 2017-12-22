/**
 * Timeago is a jQuery plugin that makes it easy to support automatically
 * updating fuzzy timestamps (e.g. "4 minutes ago" or "about 1 day ago").
 *
 * @name timeago
 * @version 1.4.0
 * @requires jQuery v1.2.3+
 * @author Ryan McGeary
 * @license MIT License - http://www.opensource.org/licenses/mit-license.php
 *
 * For usage and examples, visit:
 * http://timeago.yarp.com/
 *
 * Copyright (c) 2008-2013, Ryan McGeary (ryan -[at]- mcgeary [*dot*] org)
 */

(function (factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['jquery'], factory);
  } else {
    // Browser globals
    factory(jQuery);
  }
}(function ($) {
  $.timeago = function(timestamp) {
    if (timestamp instanceof Date) {
      return inWords(timestamp);
    } else if (typeof timestamp === "string") {
      return inWords($.timeago.parse(timestamp));
    } else if (typeof timestamp === "number") {
      return inWords(new Date(timestamp));
    } else {
      return inWords($.timeago.datetime(timestamp));
    }
  };
  var $t = $.timeago;

  $.extend($.timeago, {
    settings: {
      refreshMillis: 60000,
      allowPast: true,
      allowFuture: false,
      localeTitle: false,
      cutoff: 0,
      strings: {
        prefixAgo: null,
        prefixFromNow: null,
        suffixAgo: "前",
        suffixFromNow: "距離現在",
        inPast: '任何時候 現在',
        seconds: "不到 1 分鐘",
        minute: "大約 1 分鐘",
        minutes: "%d 分鐘",
        hour: "大約 1 小時",
        hours: "大約 %d 小時",
        day: "1 天",
        days: "%d 天",
        month: "大約 1 個月",
        months: "%d 月",
        year: "大約 1 年",
        years: "%d 年",
        wordSeparator: " ",
        numbers: []
      }
    },

    inWords: function(distanceMillis) {
      if(!this.settings.allowPast && ! this.settings.allowFuture) {
          throw 'timeago allowPast and allowFuture settings can not both be set to false.';
      }

      var $l = this.settings.strings;
      var prefix = $l.prefixAgo;
      var suffix = $l.suffixAgo;
      if (this.settings.allowFuture) {
        if (distanceMillis < 0) {
          prefix = $l.prefixFromNow;
          suffix = $l.suffixFromNow;
        }
      }

      if(!this.settings.allowPast && distanceMillis >= 0) {
        return this.settings.strings.inPast;
      }

      var seconds = Math.abs(distanceMillis) / 1000;
      var minutes = seconds / 60;
      var hours = minutes / 60;
      var days = hours / 24;
      var years = days / 365;

      function substitute(stringOrFunction, number) {
        var string = $.isFunction(stringOrFunction) ? stringOrFunction(number, distanceMillis) : stringOrFunction;
        var value = ($l.numbers && $l.numbers[number]) || number;
        return string.replace(/%d/i, value);
      }

      var words = seconds < 45 && substitute($l.seconds, Math.round(seconds)) ||
        seconds < 90 && substitute($l.minute, 1) ||
        minutes < 45 && substitute($l.minutes, Math.round(minutes)) ||
        minutes < 90 && substitute($l.hour, 1) ||
        hours < 24 && substitute($l.hours, Math.round(hours)) ||
        hours < 42 && substitute($l.day, 1) ||
        days < 30 && substitute($l.days, Math.round(days)) ||
        days < 45 && substitute($l.month, 1) ||
        days < 365 && substitute($l.months, Math.round(days / 30)) ||
        years < 1.5 && substitute($l.year, 1) ||
        substitute($l.years, Math.round(years));

      var separator = $l.wordSeparator || "";
      if ($l.wordSeparator === undefined) { separator = " "; }
      return $.trim([prefix, words, suffix].join(separator));
    },

    parse: function(iso8601) {
      var s = $.trim(iso8601);
      s = s.replace(/\.\d+/,""); // remove milliseconds
      s = s.replace(/-/,"/").replace(/-/,"/");
      s = s.replace(/T/," ").replace(/Z/," UTC");
      s = s.replace(/([\+\-]\d\d)\:?(\d\d)/," $1$2"); // -04:00 -> -0400
      s = s.replace(/([\+\-]\d\d)$/," $100"); // +09 -> +0900
      return new Date(s);
    },
    datetime: function(elem) {
      var iso8601 = $t.isTime(elem) ? $(elem).attr("datetime") : $(elem).attr("title");
      return $t.parse(iso8601);
    },
    isTime: function(elem) {
      // jQuery's `is()` doesn't play well with HTML5 in IE
      return $(elem).get(0).tagName.toLowerCase() === "time"; // $(elem).is("time");
    }
  });

  // functions that can be called via $(el).timeago('action')
  // init is default when no action is given
  // functions are called with context of a single element
  var functions = {
    init: function(){
      var refresh_el = $.proxy(refresh, this);
      refresh_el();
      var $s = $t.settings;
      if ($s.refreshMillis > 0) {
        this._timeagoInterval = setInterval(refresh_el, $s.refreshMillis);
      }
    },
    update: function(time){
      var parsedTime = $t.parse(time);
      $(this).data('timeago', { datetime: parsedTime });
      if($t.settings.localeTitle) $(this).attr("title", parsedTime.toLocaleString());
      refresh.apply(this);
    },
    updateFromDOM: function(){
      $(this).data('timeago', { datetime: $t.parse( $t.isTime(this) ? $(this).attr("datetime") : $(this).attr("title") ) });
      refresh.apply(this);
    },
    dispose: function () {
      if (this._timeagoInterval) {
        window.clearInterval(this._timeagoInterval);
        this._timeagoInterval = null;
      }
    }
  };

  $.fn.timeago = function(action, options) {
    var fn = action ? functions[action] : functions.init;
    if(!fn){
      throw new Error("Unknown function name '"+ action +"' for timeago");
    }
    // each over objects here and call the requested function
    this.each(function(){
      fn.call(this, options);
    });
    return this;
  };

  function refresh() {
    var data = prepareData(this);
    var $s = $t.settings;

    if (!isNaN(data.datetime)) {
      if ( $s.cutoff == 0 || distance(data.datetime) < $s.cutoff) {
        $(this).text(inWords(data.datetime));
      }
    }
    return this;
  }

  function prepareData(element) {
    element = $(element);
    if (!element.data("timeago")) {
      element.data("timeago", { datetime: $t.datetime(element) });
      var text = $.trim(element.text());
      if ($t.settings.localeTitle) {
        element.attr("title", element.data('timeago').datetime.toLocaleString());
      } else if (text.length > 0 && !($t.isTime(element) && element.attr("title"))) {
        element.attr("title", text);
      }
    }
    return element.data("timeago");
  }

  function inWords(date) {
    return $t.inWords(distance(date));
  }

  function distance(date) {
    return (new Date().getTime() - date.getTime());
  }

  // fix for IE6 suckage
  document.createElement("abbr");
  document.createElement("time");
}));











//進入貼文頁面
$(document).ready(function(){
	$.ajax({
		"url" : PostURL,
		"type" : "post",
		"data" : {"post_id" : post_id},
		"datatype" : "text",
		"success" : function(data) {
			var json = eval('(' + data + ')');
			if (json.posts != "") {
				$(".post").append(
						"<div class=\"page" + page + "\" id=\"" + json.post.post_id + "\">" +
						"<div class=\"scroll\" style=\"width: 700px;\">" +
						"<div class=\"outoutblock\">" +
						"<a href=\"#\" class=\"outblockspand\"><img src=\"" + imgURL + "images/post-arrow-down.png\" class=\"spandarrow\"></a>" +
						"<div class=\"outblock\">" +
						"<div class=\"c" + page + "\" id=\"c" + json.post.post_id + "\">" +
						"<div class=\"block\">" +
						"<div class=\"b" + page + "\">" +
						"<div class=\"manyicon\">" +
						"<div class=\"a" + page + "\">");
				if (user != null) {
					$(".a" + page + "").append(
							"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible like\" title=\"按個「讚」！\"><img id=\"l" + json.post.post_id + "\" src=\"" + imgURL + "images/like.png\"></a>" +
							"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible favorite\" title=\"將這則貼文加入最愛\"><img id=\"f" + json.post.post_id + "\" src=\"" + imgURL + "images/favorite.png\"></a>");
					if (user_id == json.post.m_id) {
						$(".a" + page + "").append(
								"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible edit\" title=\"編輯這則貼文\"><img id=\"ep" + json.post.post_id + "\" src=\"" + imgURL + "images/edit.png\"></a>" +
								"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible delete\" title=\"刪除這則貼文\"><img id=\"dp" + json.post.post_id + "\" src=\"" + imgURL + "images/delete.png\"></a>");
					} else {
						$(".a" + page + "").append(
						"<a href=\"#\" onclick=\"return false;\" class=\"newreport iconinvisible\" title=\"檢舉這則貼文\"><img id=\"a" + json.post.post_id + "\" src=\"" + imgURL + "images/report.png\"></a>");
					}
				} else {
					$(".a" + page + "").append(
							"<a href=\"#\" onclick=\"return false;\" class=\"newwarning iconinvisible\" title=\"發送警告\"><img src=\"" + imgURL + "images/warn.png\"></a>");
				}
				var whoLikeName = "";
				var whoLikeSum = json.post.like_count;
				if (json.whoLikes != null) {
					var count = 0;
					for (var l = 0; l < json.whoLikes.length; l++) {
						if (json.whoLikes[l].post_id == json.post.post_id && count == whoLikeSum - 1) {
							whoLikeName += json.whoLikes[l].m_name;
						} else if (json.whoLikes[l].post_id == json.post.post_id) {
							whoLikeName += json.whoLikes[l].m_name + "、";
							count++;
						}
					}
				}
				$(".b" + page + "").append(
						"</div>" +
						"<div class=\"likeblock\">" +
						"<a href=\"#\" id=\"wl" + json.post.post_id + "\" onclick=\"return false;\" class=\"liketext\" title=\"誰按讚：" + whoLikeName + "\">" + json.post.like_count + "</a>" +
						"<img src=\"" + imgURL + "images/like_arrow.png\" style=\"opacity: 0.4; filter: alpha(opacity = 40); \">"+
						"</div>");
				if (json.post.m_pic_path == null) {
					$(".b" + page + "").append(
							"<a href=\"#\" onclick=\"return false;\" style=\"text-decoration: none;\"><img src=\"" + imgURL + "images/cat1.jpg\" class=\"memberphoto\"><span class=\"membername\">" + json.post.m_name + "</span></a><span style='color: grey;'>" + jQuery.timeago(json.post.post_time) + "</span><br/>");
				} else {
					$(".b" + page + "").append(
							"<a href=\"#\" onclick=\"return false;\" style=\"text-decoration: none;\"><img src=\"" + imgURL + "userImages/" + json.post.m_id + "/" + json.post.m_pic_path + "\" class=\"memberphoto\"><span class=\"membername\">" + json.post.m_name + "</span></a><span style='color: grey;'>" + jQuery.timeago(json.post.post_time) + "</span><br/>");
				}
				$(".b" + page + "").append(
						"<div class=\"postcontent\">" +
						json.post.post_content + "<br><br>" +
						"<div class=\"waterwheelcarousel\">" +
						"<div class=\"d" + page + "\">" +
						"</div>");
				if (json.pics != null) {
					for (var j = 0; j < json.pics.length; j++) {
						if (json.pics[j].post_id == json.post.post_id) {
							$(".d" + page + "").append(
									"<img src=\"" + imgURL + "userImages/" + json.pics[j].m_id + "/" + json.pics[j].pic_file_path + "\" width=\"120\" height=\"120\" alt=\"Image " + j + "\" />");
						}
					}
				}
				$(".c" + page + "").append(
						"<div class=\"iwanttorespond\">" +
						"<textarea cols=\"52\" rows=\"0\" placeholder=\"我要留言\" class=\"respondtextarea text ui-widget-content ui-corner-all\"></textarea><a href=\"#\" onclick=\"return false;\" id=\"r" + json.post.post_id + "\" class=\"buttonClass summitresponse\">送出</a><br/>" +
						"</div>");
				if (json.res != null) {
					for (var k = 0; k < json.res.length; k++) {
						if (json.res[k].post_id == json.post.post_id) {
							$(".c" + page + "").append(
									"<div class=\"responseblock\" id=\"response\">" +
									"<a href=\"#\" onclick=\"return false;\" style=\"text-decoration: none;\"><img src=\"" + imgURL + "images/cat1.jpg\" class=\"responsememberphoto\"><span class=\"responsemembername\">" + json.res[k].m_name + "</span></a>" +
									"<span class=\"responsecontent\">" + json.res[k].res_content + "　　<span style='color: grey; font-size: 12px;'>" + jQuery.timeago(json.res[k].res_time) + "</span></span>" +
									"<div class=\"e" + page + "\">");
							if (user_id == json.post.m_id || user_id == json.res[k].m_id) {
								$(".e" + page + "").append(
										"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible deleteresponse\"><img id=\"dr" + json.res[k].res_id + "\" src=\"" + imgURL + "images/delete.png\"></a>");
							}
							$(".e" + page + "").append(
									"</div>" +
									"</div>" +
									"</div>" +
									"</div>");
						}
					}
				}
			}
		},
		"complete" : function() {
			$(".buttonClass").button();
			$(document).tooltip();
			$( ".elastic" ).elastic();
			$("#opennewpost").click(function() {
				window.parent.parent.parent.parent.$('#newpost').dialog('open');
			});
			$(".newreport").click(function() {
				window.parent.parent.parent.parent.$('#newreport').dialog('open');
			});
			$(".newwarning").click(function() {
				window.parent.parent.parent.parent.$('#newwarning').dialog('open');
			});
			$(".outblockspand").click(function() {
				var src = ($(this).children().attr("src") === "" + imgURL + "images/post-arrow-down.png") ? "" + imgURL + "images/post-arrow-up.png"  : "" + imgURL + "images/post-arrow-down.png";
				$(this).parent().children(".outblock").toggleClass("outblock2", {duration: 300});
				$(this).children().attr("src", src);
				return false;
			});
			$(".delete").click(function() {
				window.parent.parent.parent.parent.$('#deleteconfirm').dialog('open');
			});
			$(".edit").click(function() {
				window.parent.parent.parent.parent.$('#editpost').dialog('open');
			});
			$(".liketext").click(function() {
				window.parent.parent.parent.parent.$('#wholike').dialog('open');
			});
			$(".like").click(function() {
				$(this).unbind("click");
				return false;
			});

			//按讚
			$('.like').on('click',$(this).children("img").attr("id"),function() {
				var post_id = $(this).children("img").attr("id").slice(1);
				var likeCount = $(this).parent().parent().parent().children(".likeblock").children(".liketext");
				$.ajax({
					"url" : LikeURL,
					"type" : "post",
					"data" : {"post_id" : post_id},
					"datatype" : "text",
					"success" : function(data) {
						var json = eval('(' + data + ')');
						if (json.likePost == true) {
							likeCount.html(parseInt(likeCount.html())+1);
					//		parent.$().message("按讚成功！");
							if (likeCount.attr("title") == "誰按讚：") {
								likeCount.attr("title",likeCount.attr("title") + json.likeUser);

							} else {
								likeCount.attr("title",likeCount.attr("title") + "、" + json.likeUser);
							}
						} else {
					//		parent.$().message("您已按過讚！");
						}
					}
				});
			});

			//將這則貼文加入最愛
			$('.favorite').on('click',$(this).children("img").attr("id"),function() {
				var post_id = $(this).children("img").attr("id").slice(1);
				$.ajax({
					"url" : FavoriteURL,
					"type" : "post",
					"data" : {"post_id" : post_id},
					"datatype" : "text",
					"success" : function(data) {
						var json = eval('(' + data + ')');
						if (json.addPostToFavorite == true) {
							parent.parent.parent.parent.$().message("加入最愛成功！");
						} else {
							parent.parent.parent.parent.$().message("您加入過了！");
						}
					}
				});
			});

			//回應貼文
			$('.summitresponse').on('click',$(this).attr("id"),function() {
				var obj = $(this);
				var post_id = $(this).attr("id").slice(1);
				var res_content = $(this).parent().children("textarea").val();
				$.ajax({
					"url" : ResponseURL,
					"type" : "post",
					"data" : {
						"post_id" : post_id,
						"res_content" : res_content},
					"datatype" : "text",
					"success" : function(data) {
						var json = eval('(' + data + ')');
						obj.parent().parent().append(
								"<div class=\"responseblock\" id=\"response\">" +
								"<a href=\"#\" onclick=\"return false;\" style=\"text-decoration: none;\"><img src=\"" + imgURL + "images/cat1.jpg\" class=\"responsememberphoto\"><span class=\"responsemembername\">" + json.resUser + "</span></a>" +
								"<span class=\"responsecontent\">" + res_content + "<span style='color: grey; font-size: 12px;'>　　" + jQuery.timeago(json.res_time) + "</span></span>" +
								"<div>" +
								"<a href=\"#\" onclick=\"return false;\" class=\"iconinvisible deleteresponse\"><img id=\"dr" + json.res_id + "\" src=\"" + imgURL + "images/delete.png\"></a>" +
								"</div>" +
								"</div>");
					},
					"complete" : function() {
						$('.deleteresponse').on('click',$(this).children("img").attr("id"),function() {
							var obj = $(this);
							var res_id = $(this).children("img").attr("id").slice(2);
							$.ajax({
								"url" : DelResURL,
								"type" : "post",
								"data" : {
									"res_id" : res_id},
								"datatype" : "text",
								"success" : function(data) {
									obj.parent().parent().remove();
								}
							});
						});
					}
				});
			});

			//刪除回應
			$('.deleteresponse').on('click',$(this).children("img").attr("id"),function() {
				var obj = $(this);
				var res_id = $(this).children("img").attr("id").slice(2);
				$.ajax({
					"url" : DelResURL,
					"type" : "post",
					"data" : {
						"res_id" : res_id},
					"datatype" : "text",
					"success" : function(data) {
						obj.parent().parent().remove();
					}
				});
			});

			//檢舉貼文
			$('.newreport').on('click',$(this).children("img").attr("id"),function() {
				var post_id = $(this).children("img").attr("id").slice(1);
				parent.parent.parent.parent.$("#newreportiframe").attr("src", imgURL + "pages/NewReport.jsp?post_id=" + post_id);
			});

			//編輯這則貼文
			$('.edit').on('click',$(this).children("img").attr("id"),function() {
				var post_id = $(this).children("img").attr("id").slice(2);
				parent.parent.parent.parent.$("#editpostiframe").attr("src", imgURL + "pages/EditPost.jsp?post_id=" + post_id);
			});

			//刪除這則貼文
			$('.delete').on('click',$(this).children("img").attr("id"),function() {
				var post_id = $(this).children("img").attr("id").slice(2);
				parent.parent.parent.parent.$("#deleteconfirmiframe").attr("src", imgURL + "pages/DeleteConfirm.jsp?post_id=" + post_id);
			});

			//有誰按讚
			$('.liketext').on('click',$(this).attr("id"),function() {
				var post_id = $(this).attr("id").slice(2);
				parent.parent.parent.parent.$("#wholikeiframe").attr("src", imgURL + "pages/WhoLike.jsp?post_id=" + post_id);
			});
		}
	});
});