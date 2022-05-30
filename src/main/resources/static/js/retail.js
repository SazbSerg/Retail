<!--Предпросмотр картинки-->
function readURL(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image").change(function() {
    readURL(this);
});


function readURL1(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage1').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image1").change(function() {
    readURL1(this);
});

function readURL2(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage2').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image2").change(function() {
    readURL2(this);
});


function readURL3(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage3').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image3").change(function() {
    readURL3(this);
});


function readURL4(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage4').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image4").change(function() {
    readURL4(this);
});

function readURL5(input) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImage5').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$("#image5").change(function() {
    readURL5(this);
});
