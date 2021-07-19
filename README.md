# computerprestaties
- Wat staat er in de GitHub Computerprestaties
- Docker installeren op de desktop
- Terminal installeren, alleen voor Windows
- Opbouwen van de container (Standaard Testomgeving)
- Starten van de container en testen
    - Commando's om de verschillende talen te testen 

## Wat staat er in de GitHub Computerprestaties
- Map Docker met de dockerfile voor de Standaard Testomgeving
- Map Scripts met de volgende subdirectories:
    - test1 Snelste Taal
         - matrix1024.c
         - matrix1024.java
         - matrix1024.py
         -    
         - matrix2048.c
         - matrix2048.java
         - matrix2048.py   

    - test2 Welke volgorde in nesting is het snelst?
        - matrixIJK.c
        - matrixJIK.c
        - matrixJKI.c
        - matrixIKJ.c
        - matrixKIJ.c
        - matrixKJI.c

    - test3 Tiling
        - S is grootte van de tile: S=4; S=8; S=16; S=32; S=64; S=128
        - matrix_tile_4.c
        - matrix_tile_8.c
        - matrix_tile_16.c
        - matrix_tile_32.c
        - matrix_tile_64.c
        - matrix_tile_128.c
           
    - test4 Parallel processing in 1 loop!
         - IJK_matrix_i_loop.c
         - IJK_ matrix_j_loop.c
         - IJK¬_matrix_k_loop.c
         - IKJ_matrix_i_loop.c
         - IKJ_ matrix_j_loop.c
         - IJK_matrix_k_loop
- README.md (deze file)
    
## Docker installeren
- Docker downloaden: https://www.docker.com/products/docker-desktop.
- Docker Installeren voor Windows of Mac.
- Werken vanuit Terminal (Mac) of Terminal (Windows, PowerShell) 
Voor Windows Update naar WSL2 gebruik de aanwijzingen op de site: https://docs.microsoft.com/en-us/windows/wsl/install-win10

## Opbouwen van de container (Standaard Testomgeving)
De Dockerfile is om de container te bouwen. Zorg dat je pad in de terminal wijst naar de subdirectorie computerprestaties. Het commando dat begint met: docker build.... bouwt de container op

### Werken met OpenCilk (c multi-threading)
Om te kunnen werken met de OpenCilk base container moeten we eerst de container in onze lokale docker registry laden. We doen dit door eerst de OpenCilk image te downloaden (https://github.com/OpenCilk/opencilk-project/releases/download/opencilk%2Fv1.0/docker-opencilk-v1.0.tar.gz) en daarna deze met het `docker load` commando in te laden:

```bash
docker load < /path/to/docker-opencilk-v1.0.tar.gz
```
```bash
cd /path/to/git/computerprestaties
docker build . -f docker/Dockerfile -t computerprestaties
```
## Starten van de container
Je zet de container (De Standaard testomgeving) aan met het volgende commando
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash
Voor Test 4 Geef op de X het aantal cores weer dat je aan de recourses wel toewijzen.
docker run --cpus=X --memory=3g -it computerprestaties bash
```
Hierna krijg je een shell prompt van de ubuntu container (je eigen linux server). Je wordt afgeleverd in de `/opt/scripts` directory en kan de testen gaan uitvoeren.

Het ziet er als volgt uit:
```bash
root@10b9a46c0255:/opt/scripts#
```

## Test 1: Welke taal is het snelste?

De test scripts komen met verschillende n-waarde, hoe groter de n hoe langer de berekeningen. Voor n=1024:

### Java

```bash
java -cp . test-1/matrix1024.java
```
### Python

```bash
python3.8 test-1/matrix1024.py
```
### C

```bash
clang -O3 -o matrix1024 test-1/matrix1024.c && ./matrix1024
```
### Testen van de scripts met n=2048
Gebruik dezelfde soort commando's maar de scripts met een grotere N (n=2048). Let op! de testen duren echt langer.

Je kan met de resources spelen om te kijken naar de effecten. Die Resources vind je in Docker->Preferences->Resources
Probeer ook eens geen optimalisatie toe te passen en kijk naar het resultaat.

Bij C is de optimalisatie level 3 gebruikt. Probeer ook eens geen optimalisatie of level 1 en 2 optimalisatie toe te passen en kijk naar het resultaat.

Geen Optimalisatie:
```bash
clang -o matrix1024 test-1/matrix1024.c && ./matrix1024
```

## Test 2: Welke volgorde in nesting is het snelst?
We gaan de nesting testen met de taal C en n=2048.

### Variant IJK

```bash
clang -O3 -o matrixIJK test-2/matrixIJK.c && ./matrixIJK
```
Test nu ook de andere varianten in de directory `test-2` met een soortgelijk commando.
```bash
test-2
|-- matrixIJK.c
|-- matrixIKJ.c
|-- matrixJIK.c
|-- matrixJKI.c
|-- matrixKIJ.c
`-- matrixKJI.c
```

## Test 3: Tiling
We gaan tiling testen met de taal C, n=2048 en verschillende tile maten.

```bash
clang -O3 -o matrix_tile_4  test-3/matrix_tile_4.c && ./matrix_tile_4
```
Test nu ook de andere tile grootes in de directory `test-3` met een soortgelijk commando.

```bash
test-3
|-- matrix_tile_4.c
|-- matrix_tile_8.c
|-- matrix_tile_16.c
|-- matrix_tile_32.c
|-- matrix_tile_64.c
`-- matrix_tile_128.c
```

## Test 4: Parallel processing in 1 loop
We gaan gebruik maken van parallelle processing m.b.v. OpenCilk. Dat kan natuurlijk alleen als je een machine hebt met een processor met meerdere cores. We kijken naar twee varianten IJK volgorde en de IKJ volgorde van de loop.
Uit Test 2 heb je gemerkt dat de IJK variant behoorlijk trager is dan de IKJ variant. We gaan voor beide varianten kijken wat de snelheidswinst is als ke de eerste loop, de tweede loop en de derde loop parallel laat verlopen.

Het commando om de test van IKJ variant de eerste (i) loop met meerdere cores te laten draaien is:
```bash
clang -o IKJ_matrix_i_loop -fopencilk -O3 test-4/IKJ_matrix_i_loop.c && ./IKJ_matrix_i_loop
```
Het commando om de test van IKJ variant de tweede (k) loop met meerdere cores te laten draaien is:
```bash
clang -o IKJ_matrix_k_loop -fopencilk -O3 test-4/IKJ_matrix_k_loop.c && ./IKJ_matrix_k_loop
```
Leid uit deze twee commando's de andere af en voer de volledige test uit.
