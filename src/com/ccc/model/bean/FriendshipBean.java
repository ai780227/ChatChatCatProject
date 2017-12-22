package com.ccc.model.bean;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/*
 * FriendshipBean
 * table:好友關係 Friendship
 */
public class FriendshipBean implements Serializable{

	private int friend_check; //關係確認 未確認、已確認、封鎖
	private MemberBean SMB_a; //會員A 復合主鍵
	private MemberBean SMB_b; //會員B 復合主鍵

	public void setFriend_check(int friend_check) {
		this.friend_check = friend_check;
	}



	public int getFriend_check() {
		return friend_check;
	}

	public MemberBean getMemberBean_a(){
		return SMB_a;
		
	}
	public void setMemberBean_a(MemberBean SMB_a){
		this.SMB_a=SMB_a;
	}
	
	public MemberBean getMemberBean_b(){
		return SMB_b;
		
	}
	public void setMemberBean_b(MemberBean SMB_b){
		this.SMB_b=SMB_b;
	}
	
	public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof FriendshipBean)) {
            return false;
        }
 
        FriendshipBean user = (FriendshipBean) obj;
        return new EqualsBuilder()
                    .append(this.SMB_a, user.getMemberBean_a())
                    .append(this.SMB_b, user.getMemberBean_b())
                    .isEquals();
    }
 
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.SMB_a)
                    .append(this.SMB_b)
                    .toHashCode();
    }

}
