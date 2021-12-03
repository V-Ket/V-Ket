using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Photon.Realtime;
using UnityEngine.UI;
using Photon.Pun;
using TMPro;

public class ChatManager : MonoBehaviourPunCallbacks
{
    public PhotonView PV;

    public TMP_InputField inputField;
    public Text outputText;
    public ScrollRect scrollRect;


    void Start()
    {
        inputField.onEndEdit.AddListener(Send);
    }

    private void Update()
    {
        scrollRect.normalizedPosition = new Vector2(0, 0);
    }

    public void TextMeshUpdated(string text)
    {
        Debug.Log("Output string " + text);
    }

    public void Send(string text)
    {
        if(text == "" || text == null)
        {
            Debug.Log("포커스 나갑니다.");
        }
        else
        {
            Debug.Log("채팅 보내기");
            PV.RPC("ChatRPC", RpcTarget.All, PhotonNetwork.NickName + " : " + text);
            PV.RPC("BubbleRPC", RpcTarget.All, PhotonNetwork.NickName + " : " + text);
            inputField.text = "";
            inputField.ActivateInputField();
        }

    }

    [PunRPC]
    void ChatRPC(string msg)
    {
        Debug.Log("채팅 받아 쓰기");
        outputText.text += msg + "\r\n";
        
    }

}
