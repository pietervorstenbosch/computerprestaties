# computerprestaties

- Plaatsten scripts
- Dockerfile definitie
- Hoe werkt dit? Handige commando's met uitleg


## Bouwen van de container

```bash
cd /path/to/git/computerprestaties
docker build . -f docker/Dockerfile -t computerprestaties
```

## Draaien van de container
```bash
docker run --cpus=1 --memory=3g -it computerprestaties bash
```

Hierna krijg je een shell prompt vban de ubuntu container (je eigen linux server). Je wordt afgeleverd in de `/opt/scripts` directory en kan de scripts gaan draaien met de onderstaande commando's

Je kan met de resources spelen om te kijken naar de effecten.

## Draaien van het java script
```bash
java -cp . Matrix.java
```

## Draaien van het python script
```bash
python3.8 matrix.py
```

## Draaien van het C script
```bash
gcc -O3 -o matrix matrix.c && ./matrix
```

Probeer ook eens geen optimalisatie toe te passen en kijk naar het resultaat.

Bijvoorbeeld:
```bash
gcc -o matrix matrix.c && ./matrix
```
