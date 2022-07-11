import {  shade, tint } from 'polished';

const pink = '#DF8797';
const navy = '#3a4db7';
const grey = '#8e8e8e';
const red = '#f50000';
const green = '#6dea33';

const ColorSwatch = (color: string): any => {
    return {
        900: shade(0.8, color),
        800: shade(0.6, color),
        700: shade(0.4, color),
        600: shade(0.2, color),
        500: color,
        400: tint(0.2, color),
        300: tint(0.4, color),
        200: tint(0.6, color),
        100: tint(0.8, color),
    };
}

export const palette = {
    pink,
    navy,
    green,
    red,
    grey
}

export const swatches = {
    pink: ColorSwatch(pink),
    navy: ColorSwatch(navy),
    grey: ColorSwatch(grey),
    red: ColorSwatch(red),
    green: ColorSwatch(green)
}

const colors =  {
    ...swatches
}

export default colors;

