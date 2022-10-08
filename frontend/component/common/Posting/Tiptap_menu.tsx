import React from 'react';
import { Code, FormatBold, FormatUnderlined } from '@mui/icons-material';
import ImageIcon from '@mui/icons-material/Image';
import { Editor } from '@tiptap/react';

function Tiptap_menu({ editor }: { editor: Editor | null }) {
	if (!editor) return <></>;

	const addImage = (event: React.ChangeEvent<HTMLInputElement>) => {
		const { target } = event;
		if (target.files) {
			if (target.files[0]) {
				const file = target.files[0];
				const reader = new FileReader();
				reader.onloadend = (e) => {
					const data = e.target?.result;
					if (typeof data == 'string' && editor) {
						console.log(typeof data);
						editor.chain().focus().setImage({ src: data }).run();
					}
				};
				reader.readAsDataURL(file);
			}
		}
	};

	return (
		<>
			<div className='editor-menu'>
				<button
					type='button'
					aria-label='굵은글씨'
					style={{ border: 'none' }}
					onClick={() => editor.chain().focus().toggleBold().run()}
					className={editor.isActive('bold') ? 'is-active' : ''}>
					<FormatBold />
				</button>
				<button
					type='button'
					aria-label='언더라인'
					onClick={() => editor.chain().focus().toggleUnderline().run()}
					className={editor.isActive('underline') ? 'is-active' : ''}>
					<FormatUnderlined />
				</button>

				<button
					onClick={() => editor.chain().focus().toggleCodeBlock().run()}
					className={editor.isActive('codeBlock') ? 'is-active' : ''}>
					<Code />
				</button>
				<input
					type='file'
					accept='image/*'
					id='addImage'
					onChange={addImage}
					style={{ display: 'none' }}
				/>
				<label htmlFor='addImage' onClick={() => editor.chain().focus()}>
					<ImageIcon />
				</label>
			</div>
		</>
	);
}

export default Tiptap_menu;
