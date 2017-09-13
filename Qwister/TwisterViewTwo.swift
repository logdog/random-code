//
//  TwisterViewTwo.swift
//  Twister
//
//  Created by Logan Dihel on 6/15/16.
//  Copyright © 2016 Logan Dihel. All rights reserved.
//

import Foundation
import UIKit
import AVFoundation

class TwisterViewTwo: UIViewController {

    @IBOutlet weak var changeLabel: UILabel!
    @IBOutlet weak var spinButton: UIButton!
    
    @IBOutlet weak var leftFootImage: UIImageView!
    @IBOutlet weak var leftHandImage: UIImageView!
    @IBOutlet weak var rightHandImage: UIImageView!
    @IBOutlet weak var rightFootImage: UIImageView!
    @IBOutlet weak var timeLabel: UILabel!
    
    var timeLeft: Double = -1
    var automatic = false
    var timeBetweenTurns: Double = -1
    var timer = Timer()
    
    var seconds: Int = 0
    var minutes: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        changeLabel.text = "Tap to Spin"
        changeLabel.textColor = UIColor.black
        
        leftFootImage.transform = CGAffineTransform(scaleX: -1, y: 1)
        leftHandImage.transform = CGAffineTransform(scaleX: -1, y: 1)
        
        timeLeft = timeBetweenTurns
        
        if timeBetweenTurns >= -1 {
            timer = Timer.scheduledTimer(timeInterval: 1.0, target: self, selector: #selector(TwisterViewTwo.update), userInfo: nil, repeats: true)
        }
        
        if automatic {
            Timer.scheduledTimer(timeInterval: 1.0, target: self, selector: #selector(TwisterViewTwo.buttonPressed), userInfo: nil, repeats: false)
            changeLabel.text = " "
        }
    }
    
    func update() {
        seconds += 1
        timeLeft -= 1
        if timeLeft == 0 {
            buttonPressed()
        }
        
        if seconds >= 60 {
            seconds -= 60
            minutes += 1
        }
        timeLabel.text = String(format: "%02d:%02d", minutes, seconds)
    }
    
    func buttonPressed() {
        timeLeft = timeBetweenTurns
        
        UIView.animate(withDuration: 1.0, animations: {
            self.spinButton.transform = CGAffineTransform(rotationAngle: CGFloat((Double(arc4random())) / (M_PI)))
        })
        
        let labelArray = [leftFootImage, leftHandImage, rightHandImage, rightFootImage]
        let rand = Int(arc4random() % 4)
        
        changeColor(labelArray[rand]!, number: rand)
    }
    
    @IBAction func spinButtonPressed(_ sender: UIButton) {
        if !automatic {
            buttonPressed()
        }
    }
    
    func changeColor(_ imageView: UIImageView, number: Int) {
        var rand = Int(arc4random() % 4)
        var message = ""
        //hand
        if number == 1 || number == 2 {
        
            let handArray = [UIImage(named: "HandGreen"), UIImage(named: "HandYellow"), UIImage(named: "HandBlue"), UIImage(named: "HandRed")]
            if imageView.image!.isEqual(handArray[rand]){
                rand += 1
                rand %= 4
            }
            
            imageView.image = handArray[rand]
            
            if number == 1 {
                message += "Left"
            }
            else {
                message += "Right"
            }
            
            message += " Hand"
            changeLabel.text = message
            
        }
        
        //foot
        else {
            let footArray = [UIImage(named: "FootGreen"), UIImage(named: "FootYellow"), UIImage(named: "FootBlue"), UIImage(named: "FootRed")]
            if imageView.image!.isEqual(footArray[rand]){
                rand += 1
                rand %= 4
            }
            
            imageView.image = footArray[rand]
            
            if number == 0 {
                message += "Left"
            }
            else {
                message += "Right"
            }
            
            message += " Foot"
            changeLabel.text = message
        }
        
        switch rand {
        case 0:
            changeLabel.textColor = UIColor.green
            message += " Green"
        case 1:
            changeLabel.textColor = UIColor.yellow
            message += " Yellow"
        case 2:
            changeLabel.textColor = UIColor.blue
            message += " Blue"
        case 3:
            changeLabel.textColor = UIColor.red
            message += " Red"
        default:
            changeLabel.textColor = UIColor.black
        }
        
        let utterance = AVSpeechUtterance(string: message)
        utterance.voice = AVSpeechSynthesisVoice(language: "en-GB")
        utterance.rate = 0.5
        
        let synthesizer = AVSpeechSynthesizer()
        synthesizer.speak(utterance)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        timer.invalidate()
        if minutes > 0 || seconds > 20 {
            let gp = UserDefaults.standard.integer(forKey: "GamesPlayed") + 1
            UserDefaults.standard.set(gp, forKey: "GamesPlayed")
        }
    }
}
