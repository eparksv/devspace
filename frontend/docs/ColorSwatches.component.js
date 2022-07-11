import React from 'react';
import { ColorPalette, ColorItem } from '@storybook/components'

export const ColorSwatches = ({ color, colors, title, subtitle, ...rest }) => {
    return (
      	<ColorPalette {...rest}>
			<ColorItem title={title || color} subtitle={subtitle} colors={colors[color] || {}} />
		</ColorPalette>
    )
}