# CSS

## Font

There are many properties related to the font, such as the face, weight, style, etc. These properties allow you to change the style or complete look of your text.

1. Font-Family

   ```css
   font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
   ```

2. Font-Style

   ```css
   font-style: italic;
   ```

3. Font-Variant

   ```css
   font-variant: small-caps;
   ```

4. Font-Weight

   ```css
   font-weight: bold;
   ```

5. Font-Size
   ```css
   font-size: larger;
   ```
6. Font
   ```css
   font: style variant weight size family;
   ```

## Text

Text properties allow one to manipulate alignment, spacing, decoration, indentation, etc., in the document.

1. Text-Align
   ```css
   text-align: justify;
   ```
2. Letter-Spacing
   ```css
   letter-spacing: 0.15rem;
   ```
3. Text-Decoration
   ```css
   text-decoration: underline;
   ```
4. Word-Spacing
   ```css
   word-spacing: 0.25em;
   ```
5. Text-Transform
   ```css
   text-transform: uppercase;
   ```
6. Text-Indent
   ```css
   text-indent: 0.5cm;
   ```
7. Line-Height
   ```css
   line-height: normal;
   ```

## Background

As the name suggests, these properties are related to background, i.e., you can change the color, image, position, size, etc., of the document.

1. Background-Image
   ```css
   background-image: url("Path");
   ```
2. Background-Position
   ```css
   background-position: right top;
   ```
3. Background-Size
   ```css
   background-size: cover;
   ```
4. Background-Repeat
   ```css
   background-repeat: no-repeat;
   ```
5. Background-Attachment
   ```css
   background-attachment: scroll;
   ```
6. Background-Color
   ```css
   background-color: fuchsia;
   ```
7. Background
   ```css
   background: color image repeat attachment position;
   ```

## Border

Border properties are used to change the style, radius, color, etc., of buttons or other items of the document.

1. Border-Width
   ```css
   border-width: 5px;
   ```
2. Border-Style
   ```css
   border-style: solid;
   ```
3. Border-Color
   ```css
   border-color: aqua;
   ```
4. Border-Radius
   ```css
   border-radius: 15px;
   ```
5. Border
   ```css
   border: width style color;
   ```

## Box Model

In laymen's terms, the CSS box model is a container that wraps around every HTML element. It consists of margins, borders, padding, and the actual content. It is used to create the design and layout of web pages.

1. Display

   ```css
   display: block;
   ```

2. Height

   ```css
   height: fit-content;
   ```

3. Width

   ```css
   width: auto;
   ```

4. Margin

   ```css
   margin: top right bottom left;
   ```

5. Padding

   ```css
   padding: top right bottom left;
   ```

6. Overflow

   ```css
   overflow: hidden;
   ```

7. Float

   ```css
   float: none;
   ```

8. Clear

   ```css
   clear: both;
   ```

9. Visibility

   ```css
   visibility: visible;
   ```

## Colors

With the help of the color property, one can give color to text, shape, or any other object.

1. Color
   ```css
   color: cornsilk;
   ```
2. Opacity
   ```css
   opacity: 4;
   ```

## Template Layout

Specifies the visual look of the content inside a template

1. max-width
   ```css
   max-width: 800px;
   ```
2. min-width
   ```css
   min-width: 500px;
   ```
3. max-height
   ```css
   max-height: 100px;
   ```
4. min-height
   ```css
   min-height: 80px;
   ```
5. Box-Align
   ```css
   box-align: start;
   ```
6. Box-Direction
   ```css
   box-direction: normal;
   ```
7. Box-Flex
   ```css
   box-flex: normal;
   ```
8. Box-Flex-Group
   ```css
   box-flex-group: 2;
   ```
9. Box-Orient
   ```css
   box-orient: inline;
   ```
10. Box-Pack
    ```css
    box-pack: justify;
    ```
11. Box-Sizing
    ```css
    box-sizing: margin-box;
    ```

## CSS Flexbox

Flexbox is a layout of CSS that lets you format HTML easily. Flexbox makes it simple to align items vertically and horizontally using rows and columns. Items will "flex" to different sizes to fill the space. And overall, it makes the responsive design more manageable.

### Parent Properties (flex container)

1. display
   ```css
   display: flex;
   ```
2. flex-direction
   ```css
   flex-direction: row | row-reverse | column | column-reverse;
   ```
3. flex-wrap
   ```css
   flex-wrap: nowrap | wrap | wrap-reverse;
   ```
4. flex-flow
   ```css
   flex-flow: column wrap;
   ```
5. justify-content
   ```css
   justify-content: flex-start | flex-end | center | space-between |
     space-around | space-evenly | start | end | left | right... + safe | unsafe;
   ```
6. align-items
   ```css
   align-items: stretch | flex-start | flex-end | center | baseline | first
     baseline | last baseline | start | end | self-start | self-end +... safe |
     unsafe;
   ```
7. align-content
   ```css
   align-content: flex-start | flex-end | center | space-between | space-around
     | space-evenly | stretch | start | end | baseline | first baseline | last
     baseline +... safe | unsafe;
   ```

### Child Properties (flex items)

1. flex shorthand
   ```css
   flex: none | [ < "flex-grow" > < "flex-shrink" >? || < "flex-basis" >];
   flex: 50%
   ```
1. align-self
   ```css
   align-self: auto | flex-start | flex-end | center | baseline | stretch;
   ```
1. order
   ```css
   order: 5; /_ default is 0 _/
   ```
1. flex-grow
   ```css
   flex-grow: 4; /_ default 0 _/
   ```
1. flex-shrink
   ```css
   flex-shrink: 3; /_ default 1 _/
   ```
1. flex-basis
   ```css
   flex-basis: | auto; /_ default auto _/
   ```

## Table

Table properties are used to give style to the tables in the document. You can change many things like border spacing, table layout, caption, etc.

Border-Collapse
border-collapse: separate;
Empty-Cells
empty-cells: show;
Border-Spacing
border-spacing: 2px;
Table-Layout
table-layout: auto;
Caption-Side
caption-side: bottom;

## Columns

These properties are used explicitly with columns of the tables, and they are used to give the table an incredible look.

Column-Count
column-count: 10;
Column-Gap
column-gap: 5px;
Column-rule-width
column-rule-width: medium;
Column-rule-style
column-rule-style: dotted ;
Column-rule-color
column-rule-color: black;
Column-width
column-width: 10px;
Column-span
column-span: all;

## List & Markers

List and marker properties are used to customize lists in the document.

List-style-type
list-style-type: square;
List-style-position
list-style-position: 20px;
List-style-image
list-style-image: url(image.gif);
Marker-offset
marker-offset: auto;

## Animations

CSS animations allow one to animate transitions or other media files on the web page.

Animation-name
animation-name: myanimation;
Animation-duration
animation-duration: 10s;
Animation-timing-function
animation-timing-function: ease;
Animation-delay
animation-delay: 5ms;
Animation-iteration-count
animation-iteration-count: 3;
Animation-direction
animation-direction: normal;
Animation-play-state
animation-play-state: running;
Animation-fill-mode
animation-fill-mode: both;

## Transitions

Transitions let you define the transition between two states of an element.

Transition-property
transition-property: none;
Transition-duration
transition-duration: 2s;
Transition-timing-function
transition-timing-function: ease-in-out;
Transition-delay
transition-delay: 20ms;

## CSS Grid

Grid layout is a 2-Dimensional grid system to CSS that creates complex responsive web design layouts more easily and consistently across browsers.

### Parent Properties (Grid container)

display
display: grid | inline-grid;
grid-template-columns
grid-template-columns: 12px 12px 12px;
grid-template-rows
grid-template-rows: 8px auto 12px;
grid-template
grid-template: none | <grid-template-rows> / <grid-template-columns>;
column-gap
column-gap: <line-size>;
row-gap
row-gap: <line-size>;
grid-column-gap
grid-column-gap: <line-size>;
grid-row-gap
grid-row-gap: <line-size>;
gap shorthand
gap: <grid-row-gap> <grid-column-gap>;
grid-gap shorthand
grid-gap: <grid-row-gap> <grid-column-gap>;
justify-items
justify-items: start | end | center | stretch;
align-items
align-items: start | end | center | stretch;
place-items
place-items: center;
justify-content
justify-content: start | end | center | stretch | space-around | space-between | space-evenly;
align-content
align-content: start | end | center | stretch | space-around | space-between | space-evenly;
place-content
place-content: <align-content> / <justify-content> ;
grid-auto-columns
grid-auto-columns: <track-size> ...;
grid-auto-rows
grid-auto-rows: <track-size> ...;
grid-auto-flow
grid-auto-flow: row | column | row dense | column dense;
Child Properties (Grid items)
grid-column-start
grid-column-start: <number> | <name> | span <number> | span <name> | auto;
grid-column-end
grid-column-end: <number> | <name> | span <number> | span <name> | auto;
grid-row-start
grid-row-start: <number> | <name> | span <number> | span <name> | auto;
grid-row-end
grid-row-end: <number> | <name> | span <number> | span <name> | auto;
grid-column shorthand
grid-column: <start-line> / <end-line> | <start-line> / span <value>;
grid-row shorthand
grid-row: <start-line> / <end-line> | <start-line> / span <value>;
grid-area
grid-area: <name> | <row-start> / <column-start> / <row-end> / <column-end>;
justify-self
justify-self: start | end | center | stretch;
align-self
align-self: start | end | center | stretch;
place-self
place-self: center;
