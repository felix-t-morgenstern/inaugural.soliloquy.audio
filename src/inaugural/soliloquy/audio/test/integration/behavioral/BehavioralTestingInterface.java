package inaugural.soliloquy.audio.test.integration.behavioral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.Sound;

public class BehavioralTestingInterface implements ActionListener {
	private final static String BUTTON_INITIALIZE = "Initialize";
	private final static String BUTTON_PLAY = "Play";
	private final static String BUTTON_PAUSE = "Pause";
	private final static String BUTTON_STOP = "Stop";
	private final static String BUTTON_MUTE = "Mute";
	private final static String BUTTON_UNMUTE = "Unmute";
	private final static String BUTTON_HALF_VOLUME = "Half Volume";
	private final static String BUTTON_FULL_VOLUME = "Full Volume";
	private final static String BUTTON_CUSTOM_TASK = "Custom Task";
	private final static String BUTTON_PLAY_LOOP = "Play Loop";
	private final static String BUTTON_STOP_LOOP = "Stop Loop";
	
	private static BehavioralTestingInterface INTERFACE = new BehavioralTestingInterface();
	private static IntegrationTestsSetup SETUP;

	static {
		try {
			SETUP = new IntegrationTestsSetup();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static Audio AUDIO = SETUP.audio();
	private static Sound SOUND;
	private static Sound SOUND_LOOPING;
	
	private static boolean RUN_RECURRING_POSITION_CHECK = true;

	private static JLabel LABEL_ID;
	private static JLabel LABEL_TYPE_ID;
	private static JLabel LABEL_VOLUME;
	private static JLabel LABEL_DURATION;
	private static JLabel LABEL_POSITION;
	
	public static void main(String[] args) {
		log("Starting BehavioralTestingInterface...");
		
		JFrame frame = new JFrame();
		
		LABEL_ID = new JLabel("Sound Id: ");
		LABEL_ID.setBounds(160, 20, 300, 30);
		frame.add(LABEL_ID);
		
		LABEL_TYPE_ID = new JLabel("Sound Type Id: ");
		LABEL_TYPE_ID.setBounds(160, 70, 300, 30);
		frame.add(LABEL_TYPE_ID);
		
		LABEL_VOLUME = new JLabel("Volume: ");
		LABEL_VOLUME.setBounds(160, 120, 300, 30);
		frame.add(LABEL_VOLUME);
		
		LABEL_DURATION = new JLabel("Duration, ms: ");
		LABEL_DURATION.setBounds(160, 170, 300, 30);
		frame.add(LABEL_DURATION);
		
		LABEL_POSITION = new JLabel("Position, ms: ");
		LABEL_POSITION.setBounds(160, 220, 300, 30);
		frame.add(LABEL_POSITION);
		
		JButton buttonInitialize = new JButton(BUTTON_INITIALIZE);
		buttonInitialize.setBounds(20, 20, 120, 30);
		buttonInitialize.addActionListener(INTERFACE);
		frame.add(buttonInitialize);
		
		JButton buttonPlay = new JButton(BUTTON_PLAY);
		buttonPlay.setBounds(20, 70, 120, 30);
		buttonPlay.addActionListener(INTERFACE);
		frame.add(buttonPlay);
		
		JButton buttonPause = new JButton(BUTTON_PAUSE);
		buttonPause.setBounds(20, 120, 120, 30);
		buttonPause.addActionListener(INTERFACE);
		frame.add(buttonPause);
		
		JButton buttonStop = new JButton(BUTTON_STOP);
		buttonStop.setBounds(20, 170, 120, 30);
		buttonStop.addActionListener(INTERFACE);
		frame.add(buttonStop);
		
		JButton buttonMute = new JButton(BUTTON_MUTE);
		buttonMute.setBounds(20, 220, 120, 30);
		buttonMute.addActionListener(INTERFACE);
		frame.add(buttonMute);
		
		JButton buttonUnmute = new JButton(BUTTON_UNMUTE);
		buttonUnmute.setBounds(20, 270, 120, 30);
		buttonUnmute.addActionListener(INTERFACE);
		frame.add(buttonUnmute);
		
		JButton buttonHalfVolume = new JButton(BUTTON_HALF_VOLUME);
		buttonHalfVolume.setBounds(20, 320, 120, 30);
		buttonHalfVolume.addActionListener(INTERFACE);
		frame.add(buttonHalfVolume);
		
		JButton buttonFullVolume = new JButton(BUTTON_FULL_VOLUME);
		buttonFullVolume.setBounds(20, 370, 120, 30);
		buttonFullVolume.addActionListener(INTERFACE);
		frame.add(buttonFullVolume);
		
		JButton buttonCustomTask = new JButton(BUTTON_CUSTOM_TASK);
		buttonCustomTask.setBounds(20, 420, 120, 30);
		buttonCustomTask.addActionListener(INTERFACE);
		frame.add(buttonCustomTask);

		JButton buttonPlayLoop = new JButton(BUTTON_PLAY_LOOP);
		buttonPlayLoop.setBounds(20, 480, 120, 30);
		buttonPlayLoop.addActionListener(INTERFACE);
		frame.add(buttonPlayLoop);

		JButton buttonStopLoop = new JButton(BUTTON_STOP_LOOP);
		buttonStopLoop.setBounds(20, 540, 120, 30);
		buttonStopLoop.addActionListener(INTERFACE);
		frame.add(buttonStopLoop);
		
		frame.setSize(500,650);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void log(String msg) {
		System.out.println(msg);
	}
	
	private static void updateLabels() {
		LABEL_ID.setText("Sound Id: " + (SOUND == null ? "" : "" + SOUND.id()));
		LABEL_TYPE_ID.setText("Sound Type Id: " + (SOUND == null ? "" : "" + SOUND.soundType().id()));
		LABEL_VOLUME.setText("Volume: " + (SOUND == null || SOUND.isStopped() ? "" : "" + SOUND.getVolume()));
		LABEL_DURATION.setText("Duration, ms: " + (SOUND == null || SOUND.isStopped() ? "" : "" + SOUND.getMillisecondLength()));
		LABEL_POSITION.setText("Position, ms: " + (SOUND == null || SOUND.isStopped() ? "" : "" + SOUND.getMillisecondPosition()));
	}
	
	private void recurringPositionCheck() {
		while(RUN_RECURRING_POSITION_CHECK) {
			try {
				LABEL_POSITION.setText("Position, ms: " + (SOUND == null || SOUND.isStopped() ? "" : "" + SOUND.getMillisecondPosition()));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case BUTTON_INITIALIZE:
				log("Clicked " + BUTTON_INITIALIZE);
				new Thread(this::initialize).start();
				break;
			case BUTTON_PLAY:
				log("Clicked " + BUTTON_PLAY);
				new Thread(this::play).start();
				break;
			case BUTTON_PAUSE:
				log("Clicked " + BUTTON_PAUSE);
				new Thread(this::pause).start();
				break;
			case BUTTON_STOP:
				log("Clicked " + BUTTON_STOP);
				new Thread(this::stop).start();
				break;
			case BUTTON_MUTE:
				log("Clicked " + BUTTON_MUTE);
				new Thread(this::mute).start();
				break;
			case BUTTON_UNMUTE:
				log("Clicked " + BUTTON_UNMUTE);
				new Thread(this::unmute).start();
				break;
			case BUTTON_HALF_VOLUME:
				log("Clicked " + BUTTON_HALF_VOLUME);
				new Thread(this::halfVolume).start();
				break;
			case BUTTON_FULL_VOLUME:
				log("Clicked " + BUTTON_FULL_VOLUME);
				new Thread(this::fullVolume).start();
				break;
			case BUTTON_CUSTOM_TASK:
				log("Clicked " + BUTTON_CUSTOM_TASK);
				new Thread(this::customTask).start();
				break;
			case BUTTON_PLAY_LOOP:
				log("Clicked " + BUTTON_PLAY_LOOP);
				new Thread(this::playLoop).start();
				break;
			case BUTTON_STOP_LOOP:
				log("Clicked " + BUTTON_STOP_LOOP);
				new Thread(this::stopLoop).start();
				break;
			default:
				log("Unrecognized command.");
				break;
		}
		updateLabels();
	}
	
	private void initialize() {
		AUDIO.soundTypes().add(SETUP.sampleSoundType());
		AUDIO.soundTypes().add(SETUP.sampleLoopingSoundType());
		for(Sound sound : AUDIO.soundsPlaying()) {
			sound.stop();
		}
		SOUND = AUDIO.soundFactory().make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
		SOUND_LOOPING = AUDIO.soundFactory().make(IntegrationTestsSetup.SOUND_TYPE_2_ID);
		RUN_RECURRING_POSITION_CHECK = true;
		Thread runRecurringPositionCheckThread = new Thread(this::recurringPositionCheck);
		runRecurringPositionCheckThread.start();

		log("Initialized successfully.");
	}
	
	private void play() {
		try {
			SOUND.play();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void pause() {
		try {
			SOUND.pause();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void stop() {
		try {
			SOUND.stop();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void mute() {
		try {
			SOUND.mute();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void unmute() {
		try {
			SOUND.unmute();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void halfVolume() {
		try {
			SOUND.setVolume(0.5);
			updateLabels();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void fullVolume() {
		try {
			SOUND.setVolume(1.0);
			updateLabels();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}
	
	private void customTask() {
		try {
			Thread volumeTask = new Thread(() -> SOUND.setVolume(0.75));
			Thread positionTask = new Thread(() -> SOUND.setMillisecondPosition(3000));
			Thread.sleep(2000);
			volumeTask.start();
			positionTask.start();
			Thread.sleep(250);
			new Thread(BehavioralTestingInterface::updateLabels).start();
		} catch(Exception e) {
			log(e.getClass().getName() + " caught");
		}
	}

	private void playLoop() {
		SOUND_LOOPING.play();
	}

	private void stopLoop() {
		SOUND_LOOPING.stop();
	}
}
