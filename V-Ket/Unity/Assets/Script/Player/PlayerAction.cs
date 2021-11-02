using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAction : MonoBehaviour
{
    public float Speed;
    public GameManager manager;
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
    h = manager.isAction ? 0 : Input.GetAxisRaw("Horizontal");
    v = manager.isAction ? 0 : Input.GetAxisRaw("Vertical");

    bool hDown = manager.isAction ? false : Input.GetButtonDown("Horizontal");
    bool vDown = manager.isAction ? false : Input.GetButtonDown("Vertical");
    bool hUp = manager.isAction ? false : Input.GetButtonUp("Horizontal");
    bool vUp = manager.isAction ? false : Input.GetButtonUp("Vertical");


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
        if(Input.GetButtonDown("Jump")){
            Debug.Log("점프함");
            Debug.Log(scanObject);
        }
        if (Input.GetButtonDown("Jump") && scanObject != null)
        {
            Debug.Log("This is : " + scanObject.name);
            if(scanObject.name.Equals("Whale_R")){

                manager.Action(scanObject);
            }
      // �����̰�, �ȿ��� ������
      //if (scanObject.name == "Store" && scanObject.GetComponent<StoreControl>().isOpen == false)
      //{
      //    Debug.Log("������ �����ֽ��ϴ�.");
      //    scanObject.GetComponent<StoreControl>().OpenStore();
      //}
      //// �����̰�, ����������
      //else if (scanObject.name == "Store" && scanObject.GetComponent<StoreControl>().isOpen == true)
      //{

      //    Debug.Log("������ �����ֽ��ϴ�.");
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
}
