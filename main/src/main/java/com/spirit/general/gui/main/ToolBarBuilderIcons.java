package com.spirit.general.gui.main;

import javax.swing.ImageIcon;

import com.jidesoft.icons.IconsFactory;


public class ToolBarBuilderIcons {
    public static class Standard {
        public final static String NEW = "/bar/standard/new.gif";
        public final static String OPEN = "/bar/standard/open.gif";
        public final static String SAVE = "/bar/standard/save.gif";
        public final static String PERMISSION = "/bar/standard/permission.gif";
        public final static String EMAIL = "/bar/standard/e-mail.gif";
        // ----
        public final static String PRINT = "/bar/standard/print.gif";
        public final static String PRINT_PREVIEW = "/bar/standard/print-preview.gif";
        // ----
        public final static String SPELLING_GRAMMAR = "/bar/standard/spelling-grammar.gif";
        public final static String RESEARCH = "/bar/standard/research.gif";
        // ----
        public final static String CUT = "/bar/standard/cut.gif";
        public final static String COPY = "/bar/standard/copy.gif";
        public final static String PASTE = "/bar/standard/paste.gif";
        public final static String FORMAT_PAINTER = "/bar/standard/format-painter.gif";
        // ----
        public final static String UNDO = "/bar/standard/undo.gif";
        public final static String REDO = "/bar/standard/redo.gif";
        // ----
        public final static String INSERT_HYPERLINK = "/bar/standard/insert-hyperlink.gif";
        public final static String TABLES_BORDERS = "/bar/standard/tables-borders.gif";
        public final static String INSERT_TABLE = "/bar/standard/insert-table.gif";
        public final static String INSERT_EXCEL = "/bar/standard/insert-excel.gif";
        public final static String COLUMNS = "/bar/standard/columns.gif";
        public final static String DRAWING = "/bar/standard/drawing.gif";
        // ----
        public final static String DOCUMENT_MAP = "/bar/standard/document-map.gif";
        public final static String SHOW_HIDE_SYMBOL = "/bar/standard/show-hide-symbol.gif";
        public final static String HELP = "/bar/standard/help.gif";
        // ----
    }

    public static class Formatting {
        public final static String STYLE_FORMATTING = "/bar/formatting/style-formatting.gif";
        public final static String FORMAT_FONT = "/bar/formatting/format-font.gif";
        // ----

        public final static String BOLD = "/bar/formatting/bold.gif";
        public final static String ITALIC = "/bar/formatting/italic.gif";
        // ----

        public final static String ALIGN_LEFT = "/bar/formatting/align-left.gif";
        public final static String ALIGN_CENTER = "/bar/formatting/align-center.gif";
        public final static String ALIGN_RIGHT = "/bar/formatting/align-right.gif";
        public final static String JUSTIFY = "/bar/formatting/justify.gif";
        public final static String DISTRIBUTED = "/bar/formatting/distributed.gif";
        public final static String LINE_SPACING = "/bar/formatting/line-spacing.gif";
        // ----

        public final static String NUMBERING = "/bar/formatting/numbering.gif";
        public final static String BULLETS = "/bar/formatting/bullets.gif";
        public final static String DECREASE_INDENT = "/bar/formatting/decrease-indent.gif";
        public final static String INCREASE_INDENT = "/bar/formatting/increase-indent.gif";
        // ----

        public final static String HIGHLIGHT = "/bar/formatting/highlight.gif";
        public final static String OUTSIDE_BORDER = "/bar/formatting/outside-border.gif";
        public final static String FONT_COLOR = "/bar/formatting/font-color.gif";
    }

    public static class Drawing {
        public final static String SELECT_OBJECT = "/bar/drawing/select-object.gif";
        // ----

        public final static String LINE = "/bar/drawing/line.gif";
        public final static String ARROW = "/bar/drawing/arrow.gif";
        public final static String RECTANGLE = "/bar/drawing/rectangle.gif";
        public final static String OVAL = "/bar/drawing/oval.gif";
        public final static String TEXTBOX = "/bar/drawing/textbox.gif";
        public final static String VERTICAL_TEXTBOX = "/bar/drawing/vertical-textbox.gif";
        public final static String INSERT_WORDART = "/bar/drawing/insert-wordart.gif";
        public final static String INSERT_DIAGRAM_ORGCHART = "/bar/drawing/insert-diagram-orgchart.gif";
        public final static String INSERT_CLIPART = "/bar/drawing/insert-clipart.gif";
        public final static String INSERT_PICTURE = "/bar/drawing/insert-picture.gif";
        // ----

        public final static String FILL_COLOR = "/bar/drawing/fill-color.gif";
        public final static String LINE_COLOR = "/bar/drawing/line-color.gif";
        public final static String FONT_COLOR = "/bar/drawing/font-color.gif";
        public final static String LINE_STYLE = "/bar/drawing/line-style.gif";
        public final static String DASH_STYLE = "/bar/drawing/dash-style.gif";
        public final static String ARROW_STYLE = "/bar/drawing/arrow-style.gif";
        public final static String SHADOW_STYLE = "/bar/drawing/shadow-style.gif";
        public final static String THREED_STYLE = "/bar/drawing/3d-style.gif";
    }
 
    public static class Status {
        public final static String ERROR = "/bar/status/error.gif";
    }
    
    public static ImageIcon getImageIcon(String name) {
        if (name != null)
            return IconsFactory.getImageIcon(ToolBarBuilderIcons.class, name);
        else
            return null;
    }

    public static void main(String[] argv) {
        IconsFactory.generateHTML(ToolBarBuilderIcons.class);
    }
}
