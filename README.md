# Índice de términos

## Sobre este proyecto

Se desarrollo un programa en Python que oermite a usuarios gestionar de forma
dinámica el índice de términos de un libro, mientras este todavía está en
proceso de escritura.

El objetivo se realizo implementando estructuras de datos desde cero y justificando cada decisión
de diseño con un análisis de complejidad en el peor caso.

## Qué resuelve

El índice de un libro asocia cada término importante con las páginas
donde aparece (por ejemplo: "Arrays → 20-23, 30"). Como el contenido del
libro cambia constantemente durante la revisión, necesitaba una estructura
que soportara actualizaciones frecuentes sin perder eficiencia: agregar y
quitar términos, quitar páginas completas del libro (actualizando en
cascada todos los términos afectados), y renombrar términos por sinónimos
sin perder su historial de páginas.

## Cómo lo resolví

Elegí dos estructuras de datos, cada una atacando un problema distinto:

- **Trie (árbol de prefijos)** para indexar los términos. Lo elegí porque
  una de las funcionalidades que pedía el enunciado era la búsqueda por
  prefijo (por ejemplo, "bin" debía devolver "Binary search" y
  "Binary search tree"), y un Trie resuelve esto de forma natural: el
  costo de la búsqueda depende solo del largo del prefijo, no de cuántos
  términos tenga el índice.

- **AVL (árbol binario balanceado)** para las páginas de cada término.
  Descarté un BST simple porque, si las páginas llegan ya ordenadas
  (como en el archivo de carga del enunciado: "Arrays: 20, 21, 22"), un
  BST sin balancear degenera en una lista enlazada y pierdo el
  rendimiento logarítmico justo en el peor caso, que es lo que tengo que
  reportar. El AVL me garantiza O(log n) en inserción y eliminación sin
  importar el orden de llegada de los datos.

## Funcionalidades

1. **Carga inicial** de términos desde un archivo `.txt`.
2. **Ingreso manual** de términos y páginas, uno por uno.
3. **Actualizaciones**: eliminar un término, eliminar una página (con
   actualización en cascada de todos los términos afectados), y renombrar
   un término conservando sus páginas.
4. **Búsqueda por prefijo** sobre los términos del índice.
5. **Término más frecuente**: el que aparece en más páginas.
6. **Persistencia**: guardar el índice completo en un archivo y volver a
   cargarlo en otra sesión, reconstruyendo la misma estructura interna.
7. **Visualización del índice** con el formato de un libro real, con las
   páginas agrupadas en rangos consecutivos (ej. "20-23, 30").

## Estructura

```
indice-terminos/
├── src/
│   ├── main.py              # El menú principal para que el usuario interactue
│   ├── trie.py               
│   ├── avl.py                 
│   ├── indice_terminos.py    # La clase que une ambas estructuras
│   └── persistencia.py        # Guardar y cargar el índice
├── data/
│   └── entrada_ejemplo.txt   # Datos de prueba
├── tests/                    # Pruebas unitarias y de integración
├── docs/
│   └── analisis_complejidad.md  # Análisis Big O y justificación
├── requirements.txt
└── README.md
```

## Ejecucion

```bash
# Creacion y activacion del entorno virtual
python -m venv venv
source venv/bin/activate (linux)
venv/scripts/activate (windows)

# Comando para instalar dependencias requeridas
pip install -r requirements.txt

# Ejecucion del programa
python -m src.main

# Ejecucion de tests
pytest tests/
```

## Requisitos

- Python 3.10 o superior.
- No se uso ninguna librería externa para la lógica principal: Se implemento el
  Trie y el AVL completamente desde cero, sin apoyarme en `dict`, `set`
  ni módulos como `bisect` para resolver lo que debía resolver yo mismo.

## Análisis de complejidad

Se documenta la complejidad en el peor caso de cada una de las 7
funcionalidades, junto con la justificación de por qué se elegío Trie y AVL
para cada una, en `docs/analisis_complejidad.md`.

## Participantes

- Fernanda Escobar Zambrana
- Victor Pinto Mora