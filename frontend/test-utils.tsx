import { ReactElement, FC } from "react";
import { render, RenderOptions } from "@testing-library/react"
import { ThemeProvider } from "@emotion/react"
import { Themes } from './theme/themes'


const Wrapper: FC = ({ children }: any) => (
    <ThemeProvider theme={Themes.light}>{children}</ThemeProvider>
  );
  
  const customRender = (ui: ReactElement, options?: RenderOptions) =>
    render(ui, { wrapper: Wrapper, ...options });
  
  // re-export everything
  export * from "@testing-library/react";
  
  // override render method
  export { customRender as render };