using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using UnityEngine.UI;
using Photon.Pun;
using Photon.Realtime;
using Cinemachine;
using System.Runtime.InteropServices;
using TMPro;
public class ReqUser
{
    public string userId;
    public string userPassword;
}

public class PlayerAction : MonoBehaviourPunCallbacks
{
    [DllImport("__Internal")]
    private static extern void UnityStoreHook(long storeNum);

    [DllImport("__Internal")]
    private static extern void UnityOpenStoreHook(string storeInfo);

    public PhotonView PV;
    // public SpriteRenderer SR;
    public float Speed;
    public GameObject manager;
    Rigidbody2D rigid;
    Animator anim;
    float h;
    float v;
    bool isHorizonMove;
    Vector3 dirVec;
    GameObject scanObject;

    public GameObject PlayerManager;

    public GameObject SoundManager;

    public Text userNickname;

    public TMP_InputField inputField;


    private void Start()
    {

        if (PV.IsMine)
        {
            var CM = GameObject.Find("CMCamera").GetComponent<CinemachineVirtualCamera>();
            CM.Follow = transform;
            CM.LookAt = transform;
        }

        manager = GameObject.Find("GameManager");
        PlayerManager = GameObject.Find("PlayerManager");
        SoundManager = GameObject.Find("SoundManager");

        SoundManager.GetComponent<SoundManager>().OnUi();
        SoundManager.GetComponent<AudioSource>().Play();

        userNickname.text = PV.IsMine ? PhotonNetwork.NickName : PV.Owner.NickName;

        inputField = GameObject.Find("Canvas").transform.Find("ChatInputField").GetComponent<TMP_InputField>();

    }


    void Awake()
    {
        
        rigid = GetComponent<Rigidbody2D>();
        anim = GetComponent<Animator>();

        

    }

    void Update()
    {
        if (PV.IsMine && !inputField.isFocused)
        {
            h = manager.GetComponent<GameManager>().isAction ? 0 : Input.GetAxisRaw("Horizontal");
            v = manager.GetComponent<GameManager>().isAction ? 0 : Input.GetAxisRaw("Vertical");

            bool hDown = manager.GetComponent<GameManager>().isAction ? false : Input.GetButtonDown("Horizontal");
            bool vDown = manager.GetComponent<GameManager>().isAction ? false : Input.GetButtonDown("Vertical");
            bool hUp = manager.GetComponent<GameManager>().isAction ? false : Input.GetButtonUp("Horizontal");
            bool vUp = manager.GetComponent<GameManager>().isAction ? false : Input.GetButtonUp("Vertical");


            if (hDown)
                isHorizonMove = true;
            else if (vDown)
                isHorizonMove = false;
            else if (hUp || vUp)
                isHorizonMove = h != 0;


            //????????? ??????
            if (anim.GetInteger("hAxisRaw") != h)
            {
                if (h < 0)
                {
                    anim.SetBool("up", false);
                    anim.SetBool("down", false);
                    anim.SetBool("left", true);
                    anim.SetBool("right", false);
                }
                else if (h > 0)
                {
                    anim.SetBool("up", false);
                    anim.SetBool("down", false);
                    anim.SetBool("left", false);
                    anim.SetBool("right", true);
                }
                else
                {
                    anim.SetBool("up", false);
                    anim.SetBool("down", false);
                    anim.SetBool("left", false);
                    anim.SetBool("right", false);
                }
            }
            else if (anim.GetInteger("vAxisRaw") != v)
            {
                if (v < 0)
                {
                    anim.SetBool("up", false);
                    anim.SetBool("down", true);
                    anim.SetBool("left", false);
                    anim.SetBool("right", false);
                }
                else if (v > 0)
                {
                    anim.SetBool("up", true);
                    anim.SetBool("down", false);
                    anim.SetBool("left", false);
                    anim.SetBool("right", false);
                }
                else
                {
                    anim.SetBool("up", false);
                    anim.SetBool("down", false);
                    anim.SetBool("left", false);
                    anim.SetBool("right", false);
                }
            }
            else
            {
                anim.SetBool("up", false);
                anim.SetBool("down", false);
                anim.SetBool("left", false);
                anim.SetBool("right", false);
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
            if (Input.GetButtonDown("Jump"))
            {
            }
            if (Input.GetButtonDown("Jump") && scanObject != null)
            {

                if (scanObject.name == "Store")
                {
                    if(scanObject.GetComponent<Store>().isOpen == false)
                    {
                        // ?????? ??????????????? ????????? ?????????.
                        // ?????? ????????? ?????? ??????????????? ?????????????????? ????????????.
                        string storeInfo = "" + scanObject.GetComponent<Store>().islandId + scanObject.GetComponent<Store>().storeIslandNum;
                        UnityOpenStoreHook(storeInfo);

                    }
                    // ????????? ???????????? ID??? ????????? ?????? EDIT????????? ?????????
                    else if (scanObject.GetComponent<Store>().userId == PlayerManager.GetComponent<PlayerManager>().userId)
                    {
                        // ??????????????? ???????????? ?????? ?????? ????????? ???.
                        UnityStoreHook(scanObject.GetComponent<Store>().storeId);

                    }
                    // ????????? ?????? ??????? ?????????????
                    else if(scanObject.GetComponent<Store>().userId != PlayerManager.GetComponent<PlayerManager>().userId)
                    {
                        // ????????? ????????? ???????????? ?????? ????????? ????????????.
                        UnityStoreHook(scanObject.GetComponent<Store>().storeId);
                    }
                }
                // ??????
                else if (scanObject.name == "Board")
                {
                    // ????????? ???????????? ID??? ????????? OPEN <-> CLOSE ??????
                    if (scanObject.transform.parent.GetComponent<Store>().userId == PlayerManager.GetComponent<PlayerManager>().userId)
                    {
                        // ?????? ?????????????????? ?????? ?????? ?????? ????????? ????????? ?????????.
                        // DB??? storeState??? ????????? ?????? ???????????? ???????????? ??????.
                       
                        StartCoroutine(ChangeStoreState(scanObject.transform.parent.GetComponent<Store>().storeId));

                        // ?????? ?????????????????? ?????? ????????? ?????? ???????????? ????????????.
                        //PV.RPC("SetAllStore", RpcTarget.All);


                    }
                    // ????????? ???????????? ??????

                }

                if (scanObject.name.Equals("Whale_T"))
                {

                    // manager.Action(scanObject);
                    manager.GetComponent<GameManager>().WhaleSelectT();
                }
                else if (scanObject.name.Equals("Whale_B"))
                {
                    manager.GetComponent<GameManager>().WhaleSelectB();

                }
                else if (scanObject.name.Equals("Whale_L"))
                {
                    manager.GetComponent<GameManager>().WhaleSelectL();

                }
                else if (scanObject.name.Equals("Whale_R"))
                {
                    manager.GetComponent<GameManager>().WhaleSelectR();

                }

                if (scanObject.name.Equals("Boat_R") || scanObject.name.Equals("Boat_L"))
                {
                    transform.position = new Vector3(0, -5, 0);
                }
            }
        }
        

    }

    IEnumerator ChangeStoreState(long storeId)
    {
        string uri = "http://localhost:8877/store/status/" + storeId;
        //string uri = "https://k5a404.p.ssafy.io:8877/store/status/" + storeId;

        using (UnityWebRequest request = UnityWebRequest.Get(uri))
        {
            request.downloadHandler = (DownloadHandler)new DownloadHandlerBuffer();
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();
            if (request.isNetworkError || request.isHttpError)
            {
                Debug.Log(request.error);
            }
            else
            {
                PV.RPC("SetAllStore", RpcTarget.All);
                //var _request = request.downloadHandler.text;

            }
        }
    }


    public void ChangeStore()
    {
        //?????? ?????????????????? ?????? ??????????????? ??????.
        PV.RPC("SetAllStore", RpcTarget.All);
    }

    [PunRPC]
    public void SetAllStore()
    {
        //StoreManager??? ?????? ?????? ??????
        GameObject.Find("Stores").GetComponent<StoreManager>().SetAllStore();
    }

    void FixedUpdate()
    {
        Vector2 moveVec = isHorizonMove ? new Vector2(h, 0) : new Vector2(0, v);
        rigid.velocity = moveVec * Speed;

        Vector2 temp;
        temp.x = rigid.position.x;
        temp.y = rigid.position.y - 0.5f;

        //Ray
        Debug.DrawRay(temp, dirVec * 0.7f, new Color(0, 1, 0));
        RaycastHit2D rayHit = Physics2D.Raycast(temp, dirVec, 0.7f, LayerMask.GetMask("Object"));

        if (rayHit.collider != null)
        {
            scanObject = rayHit.collider.gameObject;
        }
        else
        {
            scanObject = null;
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (PV.IsMine)
        {
            if (collision.gameObject.CompareTag("map1"))
            {
                GameObject.FindGameObjectWithTag("canvas").GetComponent<PanelCtrl>().FadeCanvas(1);
                SoundManager.GetComponent<SoundManager>().PlayAudio(0);
            }
            else if (collision.gameObject.CompareTag("map2"))
            {
                GameObject.FindGameObjectWithTag("canvas").GetComponent<PanelCtrl>().FadeCanvas(2);
                SoundManager.GetComponent<SoundManager>().PlayAudio(1);
            }
            else if (collision.gameObject.CompareTag("map3"))
            {
                GameObject.FindGameObjectWithTag("canvas").GetComponent<PanelCtrl>().FadeCanvas(3);
                SoundManager.GetComponent<SoundManager>().PlayAudio(2);
            }
            else if (collision.gameObject.CompareTag("map4"))
            {
                GameObject.FindGameObjectWithTag("canvas").GetComponent<PanelCtrl>().FadeCanvas(4);
                SoundManager.GetComponent<SoundManager>().PlayAudio(3);
            }
            else if (collision.gameObject.CompareTag("map5"))
            {
                GameObject.FindGameObjectWithTag("canvas").GetComponent<PanelCtrl>().FadeCanvas(0);
                SoundManager.GetComponent<SoundManager>().PlayAudio(4);
            }
        }
    }
}
