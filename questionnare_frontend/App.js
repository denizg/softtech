import React from 'react';
import {SafeAreaView, SectionList, StyleSheet, Text, View} from 'react-native';

import Constants from 'expo-constants';

import RNPickerSelect from 'react-native-picker-select';
import Button from 'react-native-web/dist/exports/Button';

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

const App = () => (
    <SafeAreaView style={styles.container}>
        <View>
        <SectionList
            sections={DATA}
            keyExtractor={(item, index) => item + index}
            renderItem={({item}) =>
                <RNPickerSelect
                    placeholder={item.question}
                    onValueChange={(value) => console.log(value)}
                    items={item.question_type.opt}
                />
            }
            renderSectionHeader={({section: {title}}) => (
                <Text style={styles.header}>{title}</Text>
            )}

        />
        </View>
        <View style={styles.pollEndButton}>
            <Text style={styles.title}/>
                <Button
                    name='submit'
                    onPress={() => {
                        getMoviesFromApi(); //usual call like vanilla javascript, but uses this operator
                    }}
                    title="Submit">
                </Button>
        </View>
    </SafeAreaView>
);

const styles = StyleSheet.create({
    container: {
        flex: 1,
        marginTop: Constants.statusBarHeight,
        marginHorizontal: 16,
    },
    pollEndButton: {
        alignItems: 'flex-end'
    },
    header: {
        fontSize: 32,
        backgroundColor: '#fff',
        marginVertical: 10,
    },
    title: {
        textAlign: 'center',
        marginVertical: 8,
    },
    text: {
        fontSize: 30,
        alignSelf: 'center',
        color: 'red',
    },
});

const getMoviesFromApi = () => {
    return fetch('http://localhost:8080/personal_questionnare_war_exploded/rest/show-on-screen/denix')
        .then((response) => response.json())
        .then((json) => {
            return json.movies;
        })
        .catch((error) => {
            console.error(error);
        });
};

export default App;
