<?php
include "../pdo/CRUD.php";

$id = $_GET['id'];
$nama = $_GET['nama'];
$produk = $_GET['produk'];

$crud = new CRUD();

$crud->updateData($id,$nama,$produk);
