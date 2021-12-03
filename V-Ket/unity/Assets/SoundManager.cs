using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SoundManager : MonoBehaviour
{

    public AudioClip[] clips;
    public AudioSource musicsource;

    public Image soundImg;

    public Sprite[] img;

    public float preSoundVolume;

    public GameObject soundUI;

    // Start is called before the first frame update
    void Start()
    {
        musicsource = transform.GetComponent<AudioSource>();
        musicsource.volume = 0.5f;
        preSoundVolume = 50;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void OnUi()
    {
        soundUI.SetActive(true);
    }

    public void SetMusicVolume(float volume)
    {
        musicsource.volume = volume;
        if(volume == 0)
        {
            soundImg.sprite = img[0];
        }
        else
        {
            soundImg.sprite = img[1];
        }
    }

    public void changeImg()
    {
        if(musicsource.volume > 0)
        {
            preSoundVolume = musicsource.volume;
            SetMusicVolume(0);
        }
        else
        {
            SetMusicVolume(preSoundVolume);
        }
    }

    public void stopBGM()
    {
        transform.GetComponent<AudioSource>().Stop();
    }

    public void playBGM()
    {
        transform.GetComponent<AudioSource>().Play();
    }

    public void PlayAudio(int num)
    {
        transform.GetComponent<AudioSource>().clip = clips[num];
        transform.GetComponent<AudioSource>().Play();
    }


}
