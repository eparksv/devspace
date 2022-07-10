import { HtmlProps } from "next/dist/shared/lib/html-context";
import {
  ComponentPropsWithRef,
  ElementType,
  HTMLAttributes,
  ReactElement,
  ReactNode,
  RefAttributes,
} from "react";

/**
 * 'as' prop.
 * @template A Props
 */
export type As<A = any> = ElementType<A>;

/**
 * Any object.
 */
export type AnyObject = Record<keyof any, any>;

/**
 * Any function.
 */
export type AnyFunction = (...args: any) => any;

/**
 * ReactElement = ReactChild | ReactFragment | ReactPortal | boolean | null | undefined;
 */
export type wrapperElement = (element: ReactElement) => ReactElement;

/**
 * Render Prop type
 * @template P P props
 * @exmaple
 * const children = RenderProp = (props) => <div {...props} />
 */
export type RenderProp<P = AnyObject> = (props: P) => ReactNode;

/**
 */
export type Children<T = any> =
  | ReactNode
  | RenderProp<HTMLAttributes<T> & RefAttributes<T>>;

/**
 * as가 props 에 담겨져 있습니다.
 * as 에는 대체적으로 HTML Tag 가 들어갑니다. -> Button, Input ...
 * @template T the 'as' prop
 * @example
 * type ButtonOptions = Options<"button">;
 */
export type Options<T extends As = any> = { as?: T };

/**
 * 'as' Prop 에 기반한 Html props 를 자동으로 포함하는 props
 * @template O Options
 * @exmaple
 * type ButtonHTMLProps = HTMLProps<Options<"button">>
 */
export type HTMLProps<O extends Options> = {
  wrapperElement?: wrapperElement;
  children?: Children;
} & Omit<ComponentPropsWithRef<NonNullable<O["as"]>>, keyof O | "children">;

/**
 * Options & HTML Props
 * @tempalte O Options
 * @example
 * type ButtonProps = Props<Options<"button">>;
 */
export type Props<O extends Options> = O & HTMLProps<O>;
