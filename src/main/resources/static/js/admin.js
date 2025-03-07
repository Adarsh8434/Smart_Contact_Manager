console.log("Admin user");

document.querySelector("#image_file_input").addEventListener('change', function(event) {
    let file = event.target.files[0];  // ✅ Correct way to access the selected file

    if (file) {
        let reader = new FileReader();

        reader.onload = function() {
            document.querySelector("#Upload_image_preview").setAttribute("src", reader.result);
        };

        reader.readAsDataURL(file);  // ✅ Important to actually read the file
    }
});
