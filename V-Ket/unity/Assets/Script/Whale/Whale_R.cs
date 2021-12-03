using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;

public class Whale_R : MonoBehaviour
{
    public GameObject[] player = new GameObject[6];
    public GameObject char1;
    public GameObject char2;
    public GameObject char3;
    public GameObject char4;
    public GameObject char5;
    public GameObject char6;
    public GameManager manager;
    private void Awake()
    {
        char1 = GameObject.Find("char1(Clone)");
        char2 = GameObject.Find("char2(Clone)");
        char3 = GameObject.Find("char3(Clone)");
        char4 = GameObject.Find("char4(Clone)");
        char5 = GameObject.Find("char5(Clone)");
        char6 = GameObject.Find("char6(Clone)");
        //player = GameObject.Find("Player(Clone)");
    }

    public void MoveC()
    {
        Move(0, -5);
        manager.ButtonAction();
  }
  public void MoveT()
    {
        Move(17, 42);
        manager.ButtonAction();
  }
  public void MoveB()
    {
        Move(0, -55);
        manager.ButtonAction();
  }
  public void MoveL()
    {
        Move(-73, 0);
        manager.ButtonAction();
  }
  public void MoveR(){
        Move(66, -2.25f);
        manager.ButtonAction();
  }
    public void Move(float x, float y)
    {
        if (char1 != null && char1.GetComponent<PlayerAction>().PV.IsMine)
        {
            char1.transform.position = new Vector3(x, y, 0);
        }
        else if (char2 != null && char2.GetComponent<PlayerAction>().PV.IsMine)
        {
            char2.transform.position = new Vector3(x, y, 0);
        }
        else if (char3 != null && char3.GetComponent<PlayerAction>().PV.IsMine)
        {
            char3.transform.position = new Vector3(x, y, 0);
        }
        else if (char4 != null && char4.GetComponent<PlayerAction>().PV.IsMine)
        {
            char4.transform.position = new Vector3(x, y, 0);
        }
        else if (char5 != null && char5.GetComponent<PlayerAction>().PV.IsMine)
        {
            char5.transform.position = new Vector3(x, y, 0);
        }
        else if (char6 != null && char6.GetComponent<PlayerAction>().PV.IsMine)
        {
            char6.transform.position = new Vector3(x, y, 0);
        }
    }
}
