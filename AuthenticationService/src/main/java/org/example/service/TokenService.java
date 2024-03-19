package org.example.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {

    // token service class to perform the functions and implementations required to
    // create token specific to a user id through hashing algorithms
    // tokens are created using claims to identify a token with a particular user id
    //

    public static final String token_secret = "hdbqdwvuchcgyudgcoiuhhdcouewpiuhdiewguqyd";
    public String createTokenFunction(ObjectId id){
        try{
            Algorithm algoObject = Algorithm.HMAC256(token_secret);
            String token = JWT
                    .create()
                    .withClaim("userId",id.getTimestamp())
                    .withClaim("createDat", new Date())
                    .sign(algoObject);
            return token;

        } catch (UnsupportedEncodingException | JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserIdToken(String token){
        try{
            Algorithm algoObject = Algorithm.HMAC256(token_secret);
            JWTVerifier jwtVerifier = JWT.require(algoObject).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asString();

        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isTokenValid(String token){
        String userId = this.getUserIdToken(token);
        return userId!=null;
    }
}
