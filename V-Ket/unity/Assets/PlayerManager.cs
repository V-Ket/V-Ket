using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerManager : MonoBehaviour
{
    // 유저가 선택한 캐릭터 번호
    public int userChar;
    // 유저 ID 
    public string userId;
    // 유저 닉네임
    public string userNickname;
    // 

    public GameObject NetworkManager;


    private void Awake()
    {
        this.userNickname = "text용";
        this.userChar = 0;
    }

    public void SetUserId(string userId)
    {
        Debug.Log("넘어온 값 : " + userId);
        this.userId = userId;
    }
    public void SetUserNickname(string userNickname)
    {
        Debug.Log("넘어온 값 : " + userNickname);
        this.userNickname = userNickname;
    }

    public void SetUserChar(int num)
    {
        Debug.Log("넘어온 값 : " + num);
        this.userChar = num;
    }

    public void StartGame()
    {
        Debug.Log("게임을 시작합니다.");
        // 네트워크 메니저에서 connection함수 실행해서 게임 시작
        NetworkManager.GetComponent<NetworkManager>().Connect();
    }


    public void gerUserNickname(string userNickname)
    {
        this.userNickname = userNickname;
    }

    public void getUserId(string userId)
    {
        this.userId = userId;
    }

    // 유저가 캐릭터를 고르면 캐릭터 연결
    public void ConnectPlayer()
    {

    }

}
