package com.elramady.permissionscheck.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

 val colorBlue=Color(0xFF6650a4)
@Composable
fun ButtonAppWithoutBackground(

    textButton:String,
    modifier: Modifier = Modifier,
    enabled:Boolean=true,
    loading:Boolean=false,
    textColor: Color = colorBlue,
    colorButtonBackground: Color?=null,
    borderColorButtonBackground: Color?=null,
    contentPadding: PaddingValues =ButtonDefaults.ContentPadding,
    textSize: TextUnit =16.sp,
    paddingTextVertical: Dp =10.dp,
    onClickButton:()->Unit,

    ){
//    0xFF73a2b8
    var colorBackground= Color(0x4D73A2B8)
    var borderColorBackground=colorBlue

    if (borderColorButtonBackground!=null){
        borderColorBackground=borderColorButtonBackground
    }

    if (colorButtonBackground==null){
        if (loading){
            colorBackground= Color(0x4D73A2B8)
        }else{
            if (enabled){
                colorBackground=colorBlue
            }else{
                colorBackground= Color.White
            }
        }
    }else{
        colorBackground=colorButtonBackground
    }


    Button(
        elevation = ButtonDefaults.elevatedButtonElevation(0.dp,0.dp,0.dp,0.dp,0.dp),
        shape = RoundedCornerShape(15.dp),
        colors =   ButtonDefaults.buttonColors(
            containerColor = colorBackground
        ),
        border =if (loading) null else BorderStroke(color = borderColorBackground, width = 1.dp),
        modifier = modifier
            .fillMaxWidth(),
        enabled =true,
        onClick = onClickButton,
        contentPadding =contentPadding
    ) {
        if (loading){
            CircularProgressIndicator(
                modifier= Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .size(30.dp)
                    .align(androidx.compose.ui.Alignment.CenterVertically),
                color = colorBlue
            )
        }else{
            Text(
                modifier = Modifier
                    .offset(y = (1).dp)
                    .padding(vertical = paddingTextVertical),
                textAlign = TextAlign.Center,
                text = textButton,
                fontWeight = FontWeight.Bold,

                color =if (enabled) Color.White else textColor,
                fontSize = textSize,
            )
        }

    }

}