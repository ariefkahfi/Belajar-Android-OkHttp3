<?php

include "../pdo/CRUD.php";


$id = $_POST['id'];
$nama = $_POST['nama'];
$produk = $_POST['produk'];

$crud = new CRUD();

$crud->insertData($id,$nama,$produk);
