@echo off
echo ===========================================================
echo Ejercicio de la mochila.
echo ===========================================================
echo "Comienzo de ejecucion"
echo .
echo .
echo .

for /L %%k in (1,1,5) do (
	echo Prueba: %%k
	java -jar "C:\Users\andre\OneDrive\Escritorio\EDA\Mochila\dist\Mochila.jar" 
)
echo .
echo .
echo .
echo "Fin de ejecucion"
echo ===========================================================
