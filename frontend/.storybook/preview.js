import * as NextImage from 'next/image';
import { ThemeProvider, Global } from '@emotion/react';
import { Themes } from '../theme/themes'
import { GlobalStyles } from '../styles/global';

const OriginalNextImage = NextImage.default;

// storybook Image Next.js 설정.
Object.defineProperty(NextImage, "default", {
  configurable: true,
  value: (props) => <OriginalNextImage {...props} unoptimized />,
})

const withThemeProvider = (Story, context) => {
  const background = context.globals.backgrounds?.value || parameters.backgrounds.defaultColor;
  const theme = Object.values(Themes).find(theme => theme.background === background)


  return (
    <ThemeProvider theme={theme}>
      <Global styles={GlobalStyles} />
      <Story {...context} />
    </ThemeProvider>
  )
}

const withGlobalStyles = (Story, context) => (
  <>
    <Global styles={GlobalStyles} />
    <Story {...context} />
  </>
)

export const decorators = [withThemeProvider, withGlobalStyles];

export const parameters = {
  backgrounds: {
    default: 'light', 
    defaultColor: '#ffffff',
    values: [
      { name: 'light', value: '#ffffff' },
      { name: 'dark', value: '#000000' },
    ],
  },

  actions: { argTypesRegex: "^on[A-Z].*" },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
}