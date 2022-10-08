import { ComponentStory, ComponentMeta } from '@storybook/react';
import React from 'react';

import { Icon } from './index';

export default {
	title: 'Components/Icon',
	component: Icon,
} as ComponentMeta<typeof Icon>;

const Template: ComponentStory<typeof Icon> = (args) => <Icon {...args} />;

export const BasicIcon = Template.bind({});
BasicIcon.args = {
	name: 'Vane',
};
