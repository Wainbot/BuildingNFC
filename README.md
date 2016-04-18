# Building RFID

## Contributors

* Parpex Regis
* Clémenceau Damien
* Froment Jérémy

## Instalation

`` 
jetty:run install 
``

## Launch server on local

`` 
jetty:run 
``

## Launch server on server

`` 
sudo jetty:run -Djetty.port=80 > stdout.txt 2> stderr.txt &
``

## Arbre des routes 

``
/rest
|-- GET (list buildings
|-- PUT(add building)
|-- /{tagid}
|-- |-- PUT  (update building info)
|-- |-- DELETE (delete building)
|-- |-- GET  (info building + list levels)
|-- |-- POST (add level)
|-- |-- /image <br/>
|-- |-- |-- /{imageid}
|-- |-- |-- |-- GET (image)
|-- |-- /{level} <br/>
|-- |-- |-- PUT (update level info)
|-- |-- |-- DELETE (delete level)
|-- |-- |-- GET (info level)
``

## TODO

* Upgrade readme
* Add documentation
* Finish test
* Add comments to the code
* Do the routes of BuildingServices