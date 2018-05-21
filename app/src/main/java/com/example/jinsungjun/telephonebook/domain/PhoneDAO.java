package com.example.jinsungjun.telephonebook.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {
    // 다른액티비티에서도 사용 가능해야 하므로, 클래스를 domain 패키지에 따로 정의해둔다

    public static List<Phone> getPhoneList(Context context) {

        // ID, 전화번호, 이름
        List<Phone> result = new ArrayList<>();

        //1. 컨텐트리졸버 생성
        ContentResolver resolver = context.getContentResolver();

        //2. 데이터 주소(URI) 정의

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //3. 가져올 컬럼명들을 정의
        String projections[] = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID, //주소록 아이디(중복)
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, //전화번호 (데이터)
                ContactsContract.CommonDataKinds.Phone.NUMBER        // 이름
        };

        //4. 쿼리 단위로 데이터를 받는다
        String order = projections[1] + " ASC"; //이름순 정렬 ASC - 오름차순 DESC - 내림차순
        Cursor cursor = resolver.query(uri,projections,null,null,order);

        //5. 쿼리에 담긴 데이터를 반복문을 통해 꺼낸다

        if(cursor != null) {

            while(cursor.moveToNext() == true) {

                Phone phone = new Phone();

                int idx = cursor.getColumnIndex(projections[0]);
                phone.id = cursor.getString(idx);

                idx = cursor.getColumnIndex(projections[1]);
                phone.name = cursor.getString(idx);

                idx = cursor.getColumnIndex(projections[2]);
                phone.phoneNumber = cursor.getString(idx);


                result.add(phone);
            }
            cursor.close(); //자동으로 해주지만, 습관적으로 데이터를 가져오거나 파일, 네트워크를 다룰 경우 꼭 호출
        }
        return result;

    }
}
