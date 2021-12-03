using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class JumpToMain : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }
    void Update()
    {
        
    }

    // 보트와의 충돌 감지
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.collider.tag == "Boat")
        {
            // 1초 딜레이 후 JumpToMain 함수 실행
            Invoke("JumpToSampleScene", 1.0f);
        }
    }

    void JumpToSampleScene()
    {
        SceneManager.LoadScene("SampleScene");
    }

    // 충돌 중
    private void OnCollisionStay2D(Collision2D collision)
    {
        if (collision.collider.tag == "Boat")
        {
        }
    }

    // 충돌 끝남
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.collider.tag == "Boat")
        {
        }
    }
}
