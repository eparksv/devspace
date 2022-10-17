import React from 'react';
import { useEditor, EditorContent } from '@tiptap/react';
import Image from '@tiptap/extension-image';
import Underline from '@tiptap/extension-underline';
import StarterKit from '@tiptap/starter-kit';
import Tiptap_menu from './Tiptap_menu';

function Tiptap_post() {
	//console.log('post start');
	const editor = useEditor({
		extensions: [StarterKit, Image, Underline],
	});

	return (
		<>
			{/*tiptap editor는 포커스마다 컴포넌트 렌더링이 되어서 editior사용 컴포넌트끼리 묶음*/}
			<Tiptap_menu editor={editor} />
			<EditorContent editor={editor} />
		</>
	);
}

export default Tiptap_post;
