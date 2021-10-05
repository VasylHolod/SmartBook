const input = document.getElementById('inputG');
const button = document.getElementById('but_id');

let inputValue = "";

function onClick(event) {
    // console.log('event; ', event);
    inputValue = input.value;
    console.log('input value: ', inputValue);
}

input.addEventListener('input', onClick);

// input.addEventListener('input', function (event) {
//     // console.log('event; ', event);
//     inputValue = input.value;
//     console.log('input value: ', inputValue);
// });

function myFunction() {
    //function which send ajax request to the server
    $.ajax({
        url: '/user/' + inputValue,
        datatype: 'json',
        success: function (data) {
            console.log(data);
            // inputValue = document.getElementById('inputG').value = '';
            document.getElementById('inputG').value = '';
            console.log('input Value: ', inputValue);
        }
    });
}

button.addEventListener('click', myFunction);


const groupMark = fetch('http://localhost:8080//adm_verbs/grMark')
    .then(grMark1 => grMark1.json())
    .then(function (grMark) {
        for (let i = 0; i < grMark.length; i++) {
            let row = $('<tr><td>' + grMark[i].id + '</td><td>' + grMark[i].grNameUk + '</td><td>' + grMark[i].grNameEn + '</td></tr>')
            $('#myTable').append(row);
        }
    })



// (async () => {
//     let response = await fetch('http://localhost:8080//adm_verbs/grMark');
//     let grMark = await response.json();
//     for (let i=0; i<grMark.length; i++) {
//         let row = $('<tr><td>' + grMark[i].id + '</td><td>' + grMark[i].grNameUk + '</td><td>' + grMark[i].grNameEn + '</td></tr>')
//          $('#myTable').append(row);
//     }
// })()