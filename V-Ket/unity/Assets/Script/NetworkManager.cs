using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Photon.Pun;
using Photon.Realtime;

public class NetworkManager : MonoBehaviourPunCallbacks
{

    public GameObject[] playerPrefab;
    public GameObject playerManager;

    public GameObject output;
    public GameObject input;

    void Awake()
    {
        Screen.SetResolution(1280, 970, false); 
        PhotonNetwork.SendRate = 60;
        PhotonNetwork.SerializationRate = 30;

    }

    void Start()
    {
        // 임시
        Connect();
    }

    // 이 함수 불러서 네트워크 연결.
   public void Connect()
    {
        // 포톤 연결하기.
        PhotonNetwork.ConnectUsingSettings();

    }

    // ConnectUsingSettings 콜백
    public override void OnConnectedToMaster()
    {
        // 닉네임 넣을 수 있음
        // 서버에서 받아온 닉네임을 넣어주면 됨.
        PhotonNetwork.LocalPlayer.NickName = GameObject.Find("PlayerManager").GetComponent<PlayerManager>().userNickname;
        PhotonNetwork.JoinOrCreateRoom("VKet", new RoomOptions { MaxPlayers = 20 }, null);
    }

    // JoinOrCreateRoom 콜백
    public override void OnJoinedRoom()
    {
        PhotonNetwork.Instantiate("Prefab/" + playerPrefab[playerManager.GetComponent<PlayerManager>().userChar].name, new Vector3(91, 62, 0), Quaternion.identity);
        output.SetActive(true);
        input.SetActive(true);
    }

    void Update()
    {
        // 연결 끊을때 쓰면 될듯
        if(Input.GetKeyDown(KeyCode.Escape) && PhotonNetwork.IsConnected)
        {
            PhotonNetwork.Disconnect();
        }
    }

    public override void OnDisconnected(DisconnectCause cause)
    {
        // esc 키 누르거나 끊으면 어떤 행위를 할것인지.
    }
}