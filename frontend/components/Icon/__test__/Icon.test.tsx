import { Icon } from '../index';
import { render, fireEvent } from '@testing-library/react'

describe("Icon 컴포넌트 테스트", () => {
    it("Icon 컴포넌트 렌더링 테스트", () => {
        const { asFragment } = render(<Icon name="Vane" />);
        expect(asFragment()).toMatchSnapshot();
    }) 
}) 