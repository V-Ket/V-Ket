using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAction : MonoBehaviour
{
    public float Speed;

    Rigidbody2D rigid;
    Animator anim;
    float h;
    float v;
    bool isHorizonMove;
    Vector3 dirVec;
    GameObject scanObject;

    private void Start()
    {
        Camera.main.transform.parent = transform;
        Camera.main.transform.localPosition = new Vector3(0, 0, -10);
    }


    void Awake()
    {
        rigid = GetComponent<Rigidbody2D>();
        anim = GetComponent<Animator>();
    }

    void Update()
    {
        h = Input.GetAxisRaw("Horizontal");
        v = Input.GetAxisRaw("Vertical");

        bool hDown = Input.GetButtonDown("Horizontal");
        bool vDown = Input.GetButtonDown("Vertical");
        bool hUp = Input.GetButtonUp("Horizontal");
        bool vUp = Input.GetButtonUp("Vertical");


        if (hDown)
            isHorizonMove = true;
        else if (vDown)
            isHorizonMove = false;
        else if (hUp || vUp)
            isHorizonMove = h != 0;

        // Animation
        if (anim.GetInteger("hAxisRaw") != h)
        {
            anim.SetBool("isChange", true);
            anim.SetInteger("hAxisRaw", (int)h);
        }
        else if (anim.GetInteger("vAxisRaw") != v)
        {
            anim.SetBool("isChange", true);
            anim.SetInteger("vAxisRaw", (int)v);
        }
        else
        {
            anim.SetBool("isChange", false);
        }

        //Direction
        if (vDown && v == 1)
            dirVec = Vector3.up;
        else if (vDown && v == -1)
            dirVec = Vector3.down;
        else if (hDown && h == -1)
            dirVec = Vector3.left;
        else if (hDown && h == 1)
            dirVec = Vector3.right;

        // Scan Object
        if (Input.GetButtonDown("Jump") && scanObject != null)
        {
            Debug.Log("This is : " + scanObject.name);
            // 상점이고, 안열려 있으면
            //if (scanObject.name == "Store" && scanObject.GetComponent<StoreControl>().isOpen == false)
            //{
            //    Debug.Log("상점이 닫혀있습니다.");
            //    scanObject.GetComponent<StoreControl>().OpenStore();
            //}
            //// 상점이고, 열려있으면
            //else if (scanObject.name == "Store" && scanObject.GetComponent<StoreControl>().isOpen == true)
            //{

            //    Debug.Log("상점이 열려있습니다.");
            //    scanObject.GetComponent<StoreControl>().CloseStore();
            //}
        }

    }

    void FixedUpdate()
    {
        Vector2 moveVec = isHorizonMove ? new Vector2(h, 0) : new Vector2(0, v);
        rigid.velocity = moveVec * Speed;

        //Ray
        Debug.DrawRay(rigid.position, dirVec * 0.7f, new Color(0, 1, 0));
        RaycastHit2D rayHit = Physics2D.Raycast(rigid.position, dirVec, 0.7f, LayerMask.GetMask("Object"));

        if (rayHit.collider != null)
        {
            scanObject = rayHit.collider.gameObject;
        }
        else
        {
            scanObject = null;
        }
    }

    // 고래와의 충돌
    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.collider.tag == "Whale")
        {
            Debug.Log("고래와의 충돌 감지");
        }
    }

    // 충돌 중
    private void OnCollisionStay2D(Collision2D collision)
    {
        if (collision.collider.tag == "Whale")
        {
            Debug.Log("고래와의 충돌 유지");
        }
    }

    // 충돌 끝남
    private void OnCollisionExit2D(Collision2D collision)
    {
        if (collision.collider.tag == "Whale")
        {
            Debug.Log("고래와의 충돌 종료");
        }
    }

}
