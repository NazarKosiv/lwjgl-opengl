package hello.lwjgl;

import static org.lwjgl.opengl.GL46.*;

public class Renderer {

    /**
     * This method must be called each frame, before any rendering is carried
     * out. It basically clears the screen of everything that was rendered last
     * frame (using the glClear() method). The glClearColor() method determines
     * the colour that it uses to clear the screen. In this example it makes the
     * entire screen red at the start of each frame.
     */
    public void prepare() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }

    /**
     * Renders a model to the screen.
     * <p>
     * Before we can render a VAO it needs to be made active, and we can do this
     * by binding it. We also need to enable the relevant attributes of the VAO,
     * which in this case is just attribute 0 where we stored the position data.
     * <p>
     * The VAO can then be rendered to the screen using glDrawArrays(). We tell
     * it what type of shapes to render and the number of vertices that it needs
     * to render.
     * <p>
     * After rendering we unbind the VAO and disable the attribute.
     *
     * @param model - The model to be rendered.
     */
    public void render(Model model) {
        glBindVertexArray(model.getVaoID());
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }

}
