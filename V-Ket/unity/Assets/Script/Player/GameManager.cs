using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{

    public int playerNum;
    // 외부에서 싱글톤 오브젝트를 가져올때 사용할 프로퍼티
    public static GameManager instance
    {
        get
        {
            // 만약 싱글톤 변수에 아직 오브젝트가 할당되지 않았다면
            if (m_instance == null)
            {
                // 씬에서 GameManager 오브젝트를 찾아 할당
                m_instance = FindObjectOfType<GameManager>();
            }

            // 싱글톤 오브젝트를 반환
            return m_instance;
        }
    }

    private static GameManager m_instance; // 싱글톤이 할당될 static 변수

                                           //  public GameObject talkPanel;
    public GameObject whaleSelectT;
  public GameObject whaleSelectB;
  public GameObject whaleSelectL;
  public GameObject whaleSelectR;

  public bool isAction;


    public void focusing(string focus)
    {
        if (focus.Equals("true"))
        {
            UnityEngine.WebGLInput.captureAllKeyboardInput = true;
        }
        else
        {
            UnityEngine.WebGLInput.captureAllKeyboardInput = false;
        }
    }

    

    public void WhaleSelectT(){
    if(isAction){
        isAction = false;
    }else{
        isAction = true;
    }
    whaleSelectT.SetActive(isAction);
  }
  public void WhaleSelectB(){
    if(isAction){
        isAction = false;
    }else{
        isAction = true;
    }
    whaleSelectB.SetActive(isAction);
  }
  public void WhaleSelectL(){
    if(isAction){
        isAction = false;
    }else{
        isAction = true;
    }
    whaleSelectL.SetActive(isAction);
  }
  public void WhaleSelectR(){
    if(isAction){
        isAction = false;
    }else{
        isAction = true;
    }
    whaleSelectR.SetActive(isAction);
  }
  public void ButtonAction(){
    isAction = false;
    whaleSelectT.SetActive(false);
    whaleSelectB.SetActive(false);
    whaleSelectL.SetActive(false);
    whaleSelectR.SetActive(false);
  }

}
