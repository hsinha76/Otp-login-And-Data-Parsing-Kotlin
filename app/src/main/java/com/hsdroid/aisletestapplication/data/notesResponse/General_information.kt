import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class General_information (

	@SerializedName("date_of_birth") val date_of_birth : String,
	@SerializedName("date_of_birth_v1") val date_of_birth_v1 : String,
	@SerializedName("location") val location : Location,
	@SerializedName("drinking_v1") val drinking_v1 : Drinking_v1,
	@SerializedName("first_name") val first_name : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("marital_status_v1") val marital_status_v1 : Marital_status_v1,
	@SerializedName("ref_id") val ref_id : String,
	@SerializedName("smoking_v1") val smoking_v1 : Smoking_v1,
	@SerializedName("sun_sign_v1") val sun_sign_v1 : Sun_sign_v1,
	@SerializedName("mother_tongue") val mother_tongue : Mother_tongue,
	@SerializedName("faith") val faith : Faith,
	@SerializedName("height") val height : Int,
	@SerializedName("cast") val cast : String,
	@SerializedName("kid") val kid : String,
	@SerializedName("diet") val diet : String,
	@SerializedName("politics") val politics : String,
	@SerializedName("pet") val pet : String,
	@SerializedName("settle") val settle : String,
	@SerializedName("mbti") val mbti : String,
	@SerializedName("age") val age : Int
)