export type ModalProps = {
	setOpen: React.Dispatch<React.SetStateAction<boolean>>;
	setTest: React.Dispatch<React.SetStateAction<React.ReactNode>>;
	submit?: string;
};

//setTest => setModal로 바꿔야함.

export type ModalType = {};

export type WrapProps = {
	children: React.ReactNode;
};

export type ValidationProps = {
	name: string;
	maxLength: number;
};
