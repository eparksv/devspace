import "@emotion/react";
import { AppTheme } from './theme/themes'

declare module '@emotion/react' {
    export interface theme extends AppTheme { }
}
