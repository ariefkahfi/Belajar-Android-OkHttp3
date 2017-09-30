<?php
/**
 * Created by IntelliJ IDEA.
 * User: arief
 * Date: 30/09/17
 * Time: 16:29
 */

class MyPDOConnection
{
    public static function  panggilKoneksi(){
        return new PDO("mysql:host=localhost;dbname=android_broadcast1","arief","arief");
    }
}