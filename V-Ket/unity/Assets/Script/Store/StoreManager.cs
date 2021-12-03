using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

using Photon.Pun;
using Photon.Realtime;

public class StoreManager : MonoBehaviour
{

    public PhotonView PV;

    public Store[][] storeList = { new Store[10], new Store[10], new Store[10], new Store[10] };
    public resStore[][] tempStore = { new resStore[10], new resStore[10], new resStore[10], new resStore[10] };
    private void Awake()
    {
        StartCoroutine(GetStore_Coroutine());
    }

    // Start is called before the first frame update
    void Start()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                // storeControl 스크립트 배열
                storeList[i][j] = transform.GetChild(i).GetChild(j).GetComponent<Store>();
                storeList[i][j].islandId = 1001 + i;
                storeList[i][j].storeIslandNum = j;
                if (tempStore[i][j] != null)
                {
                    storeList[i][j].setStoreInfo(tempStore[i][j].storeId,
                        tempStore[i][j].userId,
                        tempStore[i][j].islandId,
                        tempStore[i][j].storeIslandNum,
                        tempStore[i][j].storeName,
                        tempStore[i][j].storeContent,
                        tempStore[i][j].storeUrl,
                        tempStore[i][j].storeStatus);
                    storeList[i][j].isOpen = true;
                    storeList[i][j].transform.GetChild(0).GetComponent<Board>().userId = tempStore[i][j].userId;
                    storeList[i][j]._openStore();
                }

            }
        }
    }

    // Update is called once per frame
    void Update()
    {

    }

    IEnumerator GetStore_Coroutine()
    {
        // 통신을 storeManager로 옮기기 합시다.

        string uri = "http://localhost:8877/store/info";
        //string uri = "https://k5a404.p.ssafy.io:8877/store/info";

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

                var _request = request.downloadHandler.text;

                resStore[] stores = JsonHelper.FromJson<resStore>("{\"Items\":" + _request + "}");

                // store저장
                foreach (resStore st in stores)
                {
                    tempStore[(int)st.islandId - 1001][(int)st.storeIslandNum] = st;
                }

            }
        }

        _SetAllStore();
    }

    public void SetAllStore()
    {
        StartCoroutine(GetStore_Coroutine());
    }

    public void _SetAllStore()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                // storeControl 스크립트 배열
                storeList[i][j] = transform.GetChild(i).GetChild(j).GetComponent<Store>();
                storeList[i][j].islandId = 1001 + i;
                storeList[i][j].storeIslandNum = j;
                if (tempStore[i][j] != null)
                {

                    storeList[i][j].setStoreInfo(tempStore[i][j].storeId,
                        tempStore[i][j].userId,
                        tempStore[i][j].islandId,
                        tempStore[i][j].storeIslandNum,
                        tempStore[i][j].storeName,
                        tempStore[i][j].storeContent,
                        tempStore[i][j].storeUrl,
                        tempStore[i][j].storeStatus);
                    storeList[i][j].isOpen = true;
                    storeList[i][j].transform.GetChild(0).GetComponent<Board>().userId = tempStore[i][j].userId;
                    storeList[i][j]._openStore();
                }

            }
        }
    }

    public void ChangeStore()
    {
        //모든 플레이어에게 함수 실행하도록 전달.
        PV.RPC("SetStore", RpcTarget.All);
    }

    [PunRPC]
    public void SetStore()
    {
        //StoreManager에 있는 함수 실행
        SetAllStore();
    }
}
