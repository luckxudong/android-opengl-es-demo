package com.jason.scenes;

import android.graphics.Color;
import com.jason.R;
import ice.animation.AlphaAnimation;
import ice.animation.Interpolator.LinearInterpolator;
import ice.animation.RotateAnimation;
import ice.engine.EngineContext;
import ice.engine.Scene;
import ice.graphic.Texture;
import ice.model.vertex.VertexBufferObject;
import ice.model.vertex.VertexData;
import ice.node.Drawable;
import ice.node.mesh.Grid;
import ice.node.mesh.Mesh;
import ice.node.particle_system.TestParticleSystem;
import ice.node.widget.Button;
import ice.node.widget.ComesMoreText;
import ice.node.widget.TextGrid;
import ice.node.widget.TextureGrid;
import ice.practical.ComesMoreTextBox;
import ice.practical.GoAfterTouchListener;
import ice.res.Res;
import ice.util.ObjLoader;

import static javax.microedition.khronos.opengles.GL10.GL_ZERO;
import static javax.microedition.khronos.opengles.GL11.GL_ONE;
import static javax.microedition.khronos.opengles.GL11.GL_SRC_ALPHA;

/**
 * User: ice
 * Date: 12-1-6
 * Time: 下午5:07
 */
public class MainScene extends Scene {

    public MainScene() {

        int appWidth = EngineContext.getAppWidth();
        int appHeight = EngineContext.getAppHeight();

        Grid grid = alphaAnimationTest();

        TestParticleSystem testParticleSystem = particleTest(appWidth, appHeight);

        Drawable objMesh = objMeshTest(appWidth, appHeight);

        TextureGrid textureGrid = textureGridTest(appWidth, appHeight);

        Button btn = buttonTest();

        TextGrid textGrid = new ComesMoreText(200, 20, 1000);
        textGrid.setText("Hello Demo !", Color.RED, 20);
        textGrid.setPos(300, getHeight());

        ComesMoreTextBox comesMoreTextBox = new ComesMoreTextBox(500, 30, 1000);
        comesMoreTextBox.setTexts(new String[]{"ajkfjdsakfjaskfjka", "你好，呵呵"});
        comesMoreTextBox.setPos(getWidth() / 2, getHeight() - 20);
        comesMoreTextBox.enableBlend(GL_ONE, GL_ZERO);

        addChildren(grid, objMesh, textureGrid, testParticleSystem, btn, textGrid, comesMoreTextBox);
    }

    private Button buttonTest() {
        final Button btn = new Button(R.drawable.image2, R.drawable.mask2);

        btn.enableBlend(GL_ONE, GL_ONE);

        btn.setPos(0, btn.getHeight());

        RotateAnimation rotate = new RotateAnimation(2000, 0, 360);
        rotate.setRotateVector(1, 0, 0);
        rotate.setLoop(true);
        rotate.setCenterOffset(btn.getWidth() / 2, -btn.getHeight() / 2, 0);
        btn.startAnimation(rotate);
        btn.setCallFace(false);
        return btn;
    }

    private Grid alphaAnimationTest() {
        Grid grid = new Grid(50, 50);
        grid.setPos(0, 0, -200);
        grid.setCallFace(false);
        grid.setTexture(new Texture(R.drawable.star));
        grid.enableBlend(GL_SRC_ALPHA, GL_ONE);


        AlphaAnimation alphaAnimation = new AlphaAnimation(3000, 1, 0);
        alphaAnimation.setLoop(true);
        grid.startAnimation(alphaAnimation);
        return grid;
    }

    private TextureGrid textureGridTest(int appWidth, int appHeight) {
        TextureGrid textureGrid = new TextureGrid(R.drawable.image2);
        textureGrid.setPos(0, textureGrid.getHeight());
        textureGrid.enableBlend(GL_ONE, GL_ONE);
        textureGrid.setOnTouchListener(new GoAfterTouchListener());
        return textureGrid;
    }

    private TestParticleSystem particleTest(int appWidth, int appHeight) {

        TestParticleSystem testParticleSystem = new TestParticleSystem(
                50,
                new Texture(R.drawable.spark)
        );

        testParticleSystem.enableBlend(GL_ONE, GL_ONE);

        testParticleSystem.setPos(appWidth / 2, appHeight / 2, -200);

        RotateAnimation rotateAnimation = new RotateAnimation(10000, 0, 360);
        rotateAnimation.setRotateVector(1, 1, 1);
        rotateAnimation.setLoop(true);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        testParticleSystem.startAnimation(rotateAnimation);
        return testParticleSystem;
    }

    private Drawable objMeshTest(int appWidth, int appHeight) {
        ObjLoader objLoader = new ObjLoader();
        objLoader.loadObj(Res.openAssets("teaport.obj"));

        VertexData vertexData = new VertexBufferObject(objLoader.getVertexNum(), objLoader.getAttributes());
        vertexData.setVertices(objLoader.getVertexData());

        Mesh objMesh = new Mesh(vertexData);

        objMesh.setPos(0.85f * appWidth, appHeight / 2, -300);

        objMesh.setCallFace(false);

        objMesh.bindTexture(new Texture(R.drawable.mask1));

        RotateAnimation rotateAnimation = new RotateAnimation(10000, 0, 360);
        rotateAnimation.setRotateVector(1, 1, 1);
        rotateAnimation.setLoop(true);
        rotateAnimation.setInterpolator(new LinearInterpolator());

        objMesh.startAnimation(rotateAnimation);
        objMesh.setCallFace(false);

        return objMesh;
    }
}