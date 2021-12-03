using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.EventSystems;

public class MouseClick : MonoBehaviour
{

    public GameObject SoundManager;

    public void OnPointerClick(PointerEventData eventData)
    {
        SoundManager.GetComponent<SoundManager>().changeImg();
    }

}
