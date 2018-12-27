package com.example.leshchinskaya.marvelapp.model

import io.reactivex.Observable
import com.example.leshchinskaya.marvelapp.network.MarvelAPI
import com.example.leshchinskaya.marvelapp.network.Constants
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.codec.binary.Hex
import java.util.*

class RepoHeroes(val marvelAPI: MarvelAPI) {
    fun getHeroes(offset: Int): Observable<CharacterDataWrapper>{
        return marvelAPI.getHeroes(Calendar.MILLISECOND.toString(),
                Constants.PublicKey,
                String(Hex.encodeHex(DigestUtils.md5(Calendar.MILLISECOND.toString()+ Constants.PrivateKey+ Constants.PublicKey))),
                Constants.Limit, offset* Constants.Limit)
    }

    fun getCharacter(characterId: Int): Observable<CharacterDataWrapper> {
        return marvelAPI.getCharacter(characterId, Calendar.MILLISECOND.toString(),
                Constants.PublicKey,String(Hex.encodeHex(DigestUtils.md5(Calendar.MILLISECOND.toString()+ Constants.PrivateKey+ Constants.PublicKey))))
    }
}