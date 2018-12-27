package com.example.leshchinskaya.marvelapp.model

import com.example.leshchinskaya.marvelapp.network.Constants
import com.example.leshchinskaya.marvelapp.network.MarvelAPI
import io.reactivex.Observable
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.util.*

class RepoComic(val marvelAPI: MarvelAPI) {
    fun getComicsByCharacter(characterId: Int,offset: Int): Observable<ComicDataWrapper> {
        return marvelAPI.getComicsByCharacter(characterId,
                Calendar.MILLISECOND.toString(),
                Constants.PublicKey,
                String(Hex.encodeHex(DigestUtils.md5(Calendar.MILLISECOND.toString() + Constants.PrivateKey + Constants.PublicKey))),
                Constants.Limit, offset * Constants.Limit)
    }
}