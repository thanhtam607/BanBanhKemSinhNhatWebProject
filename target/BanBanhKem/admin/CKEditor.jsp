<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>A Simple Page with CKEditor 4</title>
  <!-- Make sure the path to CKEditor is correct. -->
  <script src="libraries/ckeditor/ckeditor.js"></script>
</head>
<body>
<form>
    <textarea name="editor1" id="editor1" rows="10" cols="80" class="ckeditor">
                This is my textarea to be replaced with CKEditor 4.
    </textarea>
    <button type="submit"  value="submit"></button>
</form>
<script>
    // Replace the <textarea id="editor1"> with a CKEditor 4
    // instance, using default configuration.
    CKEDITOR.replace('editor1');
</script>
</body>
</html>