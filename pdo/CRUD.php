<?php


include "MyPDOConnection.php";

class CRUD
{


    public function selectProduk(){
        $produk = array();

        $data = MyPDOConnection::panggilKoneksi()->query("select * from produk");


        while ($rs = $data->fetch(PDO::FETCH_ASSOC)){
            array_push($produk,$rs['nama_produk']);
        }

        echo json_encode(array('produk'=>$produk));

    }


    public function insertData($id,$nama,$produk){


        $pdo = MyPDOConnection::panggilKoneksi();

        $st = $pdo->prepare("insert into customer  values (?,?,?)");

        $st->bindParam(1,$id,PDO::PARAM_STR);
        $st->bindParam(2,$nama,PDO::PARAM_STR);
        $st->bindParam(3,$produk,PDO::PARAM_STR);

        $b = $st->execute();

        if($b){
            echo "Data berhasil dimasukkan ke dalam database";
        }
    }

    public function selectData(){
       $arr = array();

       $data = MyPDOConnection::panggilKoneksi()->query("select * from customer");

       while ($rs = $data->fetch(PDO::FETCH_ASSOC)){
           array_push($arr,$rs);
       }

       echo json_encode(array('person'=>$arr));
    }



    public function deleteData($id){
       $delete = MyPDOConnection::panggilKoneksi()->prepare("delete from customer where id = ? ");
       $delete->bindParam(1,$id,PDO::PARAM_STR);

       $b = $delete->execute();

       if($b){
           echo "Delete data berhasil";
       }
    }

    public function getOne($id){
       $person = array();

       $con = MyPDOConnection::panggilKoneksi();

       $pre = $con->prepare("select * from customer where id = ? ");

       $pre->bindParam(1,$id,PDO::PARAM_STR);

       $pre->execute();

       $rs = $pre->fetch(PDO::FETCH_ASSOC);

       array_push($person,$rs);

       echo json_encode(array('person'=>$person));

    }

    public function updateData($oldId , $newNama  , $newProduk){
       $con = MyPDOConnection::panggilKoneksi();

       $pre = $con->prepare("update customer set nama = ? , produk = ? where id = ? ");

       $pre->bindParam(1,$newNama,PDO::PARAM_STR);
       $pre->bindParam(2,$newProduk,PDO::PARAM_STR);
       $pre->bindParam(3,$oldId,PDO::PARAM_STR);

       $b = $pre->execute();

       if($b){
           echo "update data berhasil";
       }

    }
}