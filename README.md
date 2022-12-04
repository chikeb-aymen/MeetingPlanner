[![Level](https://img.shields.io/badge/Level-Educational-green.svg?style=flat-square)](#contributors-)

<h1 align="center">
  <!--<img src="https://cdn-images-1.medium.com/max/724/1*hKyTVR9bPKqgurtmgPcnFg@2x.png" style="width:200px;"/>-->
    <br>
  <a href="https://github.com/a-chikeb/Quiz_AndroidApp">
    Meeting Planner
  </a>
</h1>
<br>

<p align="center">
    <a href="#">
        <img src="https://img.shields.io/badge/Maven-da4b2a?style=for-the-badge&logo=maven&logoColor=white" />
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/SpringBoot-3DDC84?style=for-the-badge&logo=spring&logoColor=white" alt="Android Logo" />
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/PostgreSql-blue?style=for-the-badge&logo=postgresql&logoColor=white" alt="Firebase" />
    </a>
    <a href="#">
        <img src="https://img.shields.io/badge/Docker-white?style=for-the-badge&logo=docker&logoColor=4893cb" alt="Firebase" />
    </a>
<a href="#">
        <img src="https://img.shields.io/badge/sonarqube-4893cb?style=for-the-badge&logo=sonarqube&logoColor=white" alt="Firebase" />
    </a>
</p>
<br>

## Gestionnaire des salles de réunion d’une PME
🏆 C'est une application qui propose aux collaborateurs les meilleurs salles pour faire leur réunion selon
l'heure de reservation, le nombre de personne et le type de réunion

Il y a 4 types de réunion :
- Les visioconférences (VC) avec les partenaires qui nécessitent un écran, une
pieuvre et une webcam;
- Les séances de partage et d'études de cas (SPEC) nécessitent juste un
tableau
- Les réunions simples (RS) entre collègues sur sites nécessitent
juste d’une salle dont la capacité est au delà de 3 places;
- Les réunions couplées (RC) entre collègues sur site et en télétravail
nécessitent un tableau, un écran et une pieuvre.
<br>

## APIs
#### Pour voir les informations de toutes les salles

```
http://localhost:<port>/api/v1/room
```

#### Pour voir le details d'une salle avec nom

```
http://localhost:<port>/api/v1/room/<room-name>
```

#### Pour voir les salles qui sont disponibles par type de reunion

```
http://localhost:<port>/api/v1/room/available/<reunion-type>

body/json
{
    "startDate":"2022-12-02T08:00:00",
    "endDate":"2022-12-02T09:00:00",
    "meetingType":"RS"
}

```
#### Pour voir les salles qui sont disponibles par nombre de places

```
http://localhost:<port>/api/v1/room/nb_place/<nb-place>

exemple
http://localhost:<port>/api/v1/room/nb_place/8
```

#### Pour voir les salles qui sont disponibles par reservation information

```
http://localhost:<port>/api/v1/reservation/room/available

body/json
{
    "startDate":"2022-12-30T09:00:00",
    "endDate":"2022-12-30T10:00:00",
    "meetingType":"RS",
    "nbPlace":4
}
```


#### Pour réserver une salle en passant les informations de la reservation
```
http://localhost:<port>/api/v1/reservation

body/json
{
    "startDate":"2022-12-30T09:00:00",
    "endDate":"2022-12-30T10:00:00",
    "meetingType":"RS",
    "nbPlace":4
}
```

#### Pour voir les salles qui sont disponibles par type de reunion
```
http://localhost:<port>/api/v1/reservation/meeting/<meeting-type>

exemple
http://localhost:<port>/api/v1/reservation/meeting/RS
```

#### Pour voir toutes les reservation faites dans une salle specific
```
http://localhost:<port>/api/v1/reservation/room/<romm-name>

exemple
http://localhost:<port>/api/v1/reservation/room/E1001
```

#### Pour voir toutes les reservation faites aujourd'hui
```
http://localhost:<port>/api/v1/reservation/thisDay

```