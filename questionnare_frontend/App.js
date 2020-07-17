import React from 'react';
import {SafeAreaView, SectionList, StyleSheet, Text, View} from 'react-native';

import Constants from 'expo-constants';

import RNPickerSelect from 'react-native-picker-select';

const DATA = [
    {
        title: 'Hard Facts',
        data: [
            {
                'question': {
                    'label': 'What is your gender?',
                    'value': null,
                    'color': '#9EA0A4',
                },
                'category': 'hard_fact',
                'question_type': {
                    'type': 'single_choice',
                    'opt': [
                        {'label': 'male', 'value': 'male'},
                        {'label': 'female', 'value': 'female'},
                        {'label': 'other', 'value': 'other'},
                    ],
                },
            },
            {
                'question': {
                    'label': 'How important is the gender of your partner?',
                    'value': null,
                    'color': '#9EA0A4',
                },
                'category': 'hard_fact',
                'question_type': {
                    'type': 'single_choice',
                    'opt': [
                        {'label': 'not important', 'value': 'not important'},
                        {'label': 'important', 'value': 'important'},
                        {'label': 'very important', 'value': 'very important'},
                    ],
                },
            },
            {
                'question': {
                    'label': 'How important is the age of your partner to you?',
                    'value': null,
                    'color': '#9EA0A4',
                },
                'category': 'hard_fact',
                'question_type': {
                    'type': 'single_choice',
                    'opt': [
                        {'label': 'not important', 'value': 'not important'},
                        {'label': 'important', 'value': 'important'},
                        {'label': 'very important', 'value': 'very important'},
                    ],
                },
            },
        ],
    },
    {
        title: 'Sides',
        data: [{
            'question': {
                'label': 'What is your gender?',
                'value': null,
                'color': '#9EA0A4',
            },
            'category': 'hard_fact',
            'question_type': {
                'type': 'single_choice',
                'opt': [
                    {'label': 'not important', 'value': 'not important'},
                    {'label': 'important', 'value': 'important'},
                ],
            },
        },
        ],
    },
];

const sports = [
    {
        label: 'Football',
        value: 'football',
        question: 'fasdfa22',
    },
    {
        label: 'Baseball',
        value: 'baseball',
        question: 'fasdfasdfs',
    },
    {
        label: 'Hockey',
        value: 'hockey',
        question: 'fasdfa',
    },
];

const question =
    {
        'question': {
            'label': 'What is your gender?',
            'value': null,
            'color': '#9EA0A4',
        },
        'category': 'hard_fact',
        'question_type': {
            'type': 'single_choice',
            'opt': [
                {'label': 'not important', 'value': 'not important'},
                {'label': 'important', 'value': 'important'},
            ],
        },
    };


const Item = ({title}) => (
    <View style={styles.item}>
        <Text style={styles.title}>{title}</Text>
    </View>
);

const App = () => (
    <SafeAreaView style={styles.container}>
        <SectionList
            sections={DATA}
            keyExtractor={(item, index) => item + index}
            renderItem={({item}) =>
                <RNPickerSelect
                    placeholder={item.question}
                    onValueChange={(value) => console.log(value)}
                    items={question.question_type.opt}
                />
            }
            renderSectionHeader={({section: {title}}) => (
                <Text style={styles.header}>{title}</Text>
            )}

        />
    </SafeAreaView>

);

const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: Constants.statusBarHeight,
        marginHorizontal: 16,
    },
    item: {
        backgroundColor: '#f9c2ff',
        padding: 20,
        marginVertical: 8,
    },
    header: {
        fontSize: 32,
        backgroundColor: '#fff',
        marginVertical: 10,
    },
    title: {
        fontSize: 24,
    },
    text: {
        fontSize: 30,
        alignSelf: 'center',
        color: 'red',
    },
});

export default App;
