import React from 'react';
import { useEditor, EditorContent } from '@tiptap/react';
import Image from '@tiptap/extension-image';
import Underline from '@tiptap/extension-underline';
import StarterKit from '@tiptap/starter-kit';
import Tiptap_menu from './Tiptap_menu';

function Tiptap_post() {
	console.log('post start');
	const editor = useEditor({
		extensions: [StarterKit, Image, Underline],
	});

	return (
		<>
			<Tiptap_menu editor={editor} />
			<EditorContent editor={editor} />
		</>
	);
}

export default Tiptap_post;
