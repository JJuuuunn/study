function getLocation() {
    navigator.geolocation.getCurrentPosition(sucess, fail);
}

function sucess(position) {
    const x = position.coords.longitude;
    const y = position.coords.latitude;

    $.ajax({
        url: `https://dapi.kakao.com/v2/local/geo/coord2address.json?x=` + x + '&y=' + y +'&input_coord=WGS84',
        type: 'GET',
        headers: {
            'Authorization': 'KakaoAK 7f907e083d5446b06dcaedf6dce42198'
        },
        success: function (data) {
            const address_name = data.documents[0].road_address.address_name;
            document.getElementById('address_kakao').setAttribute('value', address_name)
        },
        error: function (e) {
            console.log(e);
            alert("주소를 가져오는데 실패하였습니다.");
        }
    });
}

function fail(error) {
    console.log(error.message);
    alert("현재 위치를 확인 할 수 없습니다.");
}
