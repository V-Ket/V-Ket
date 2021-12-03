using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class resUser
{
	public string accessToken;
	public string message;
	public int statusCode;
	public long userSeq;
	public string userId;
	public string userPhone;
	public string userNickname;
	public long userCharacter;

	public string getUserId()
    {
		return this.userId;
    }

}
